package com.florianwoelki.nong.entity;

import com.florianwoelki.nong.Game;
import com.florianwoelki.nong.neuralnetwork.NeuralNetwork;
import com.florianwoelki.nong.neuralnetwork.exception.NNNotFullyMeshedException;
import com.florianwoelki.nong.neuralnetwork.exception.NotSameAmountOfNeuronsException;
import com.florianwoelki.nong.neuralnetwork.neuron.InputNeuron;
import com.florianwoelki.nong.neuralnetwork.neuron.WorkingNeuron;

import java.awt.*;

/**
 * Created by Florian Woelki on 15.12.16.
 */
public class Player extends Entity {

    public static final float MOVE_SPEED = 3;
    public static final int PLAYER_WIDTH = 125;
    public static final int PLAYER_HEIGHT = 8;

    private final String NAME_IN_BIAS = "Bias";
    private final String NAME_IN_BALL_X = "Ball X";
    private final String NAME_IN_PADDLE_X = "Paddle X";

    private final String NAME_OUT_MOVE_DIRECTION = "Move Direction";

    private InputNeuron inBias = new InputNeuron();
    private InputNeuron inBallX = new InputNeuron();
    private InputNeuron inPaddleX = new InputNeuron();

    private WorkingNeuron outMoveDirection = new WorkingNeuron();

    private NeuralNetwork brain;

    public Player( Game game ) {
        super( game, game.getWidth() / 2 - Player.PLAYER_WIDTH / 2, game.getHeight() - 20 );

        this.width = Player.PLAYER_WIDTH;
        this.height = Player.PLAYER_HEIGHT;

        this.inBias.setName( this.NAME_IN_BIAS );
        this.inBallX.setName( this.NAME_IN_BALL_X );
        this.inPaddleX.setName( this.NAME_IN_PADDLE_X );

        this.outMoveDirection.setName( this.NAME_OUT_MOVE_DIRECTION );

        this.brain = new NeuralNetwork();

        this.brain.addInputNeuron( this.inBias );
        this.brain.addInputNeuron( this.inBallX );
        this.brain.addInputNeuron( this.inPaddleX );

        this.brain.generateHiddenNeurons( 3 );

        this.brain.addOutputNeuron( this.outMoveDirection );

        this.brain.generateFullMesh();

        this.brain.randomizeAllWeights();
    }

    @Override
    public void render( Graphics g ) {
        g.setColor( Color.WHITE );
        g.fillRect( (int) this.x, (int) this.y, this.width, this.height );
    }

    @Override
    public void update() {
        this.x += this.dx;
        this.y += this.dy;

        // Handle input
        if ( this.game.getKeyboard().isSpaceActivated ) {
            if ( this.game.getKeyboard().left ) {
                this.dx = -Player.MOVE_SPEED;
            } else if ( this.game.getKeyboard().right ) {
                this.dx = Player.MOVE_SPEED;
            } else {
                this.dx = this.dy = 0;
            }
        } else {
            this.setInputValues();
            this.act();

            try {
                this.brain = this.brain.cloneFullMesh();
            } catch ( NNNotFullyMeshedException | NotSameAmountOfNeuronsException e ) {
                e.printStackTrace();
            }

            this.inBias = this.brain.getInputNeuronFromName( this.NAME_IN_BIAS );
            this.inBallX = this.brain.getInputNeuronFromName( this.NAME_IN_BALL_X );
            this.inPaddleX = this.brain.getInputNeuronFromName( this.NAME_IN_PADDLE_X );

            this.outMoveDirection = this.brain.getOutputNeuronFromName( this.NAME_OUT_MOVE_DIRECTION );
        }

        // Handle collision with game window
        if ( this.x <= 0 ) {
            this.x = 0;
        } else if ( this.x >= this.game.getWidth() - this.width ) {
            this.x = this.game.getWidth() - this.width;
        }
    }

    private void setInputValues() {
        this.inBias.setValue( 1f );
        this.inBallX.setValue( this.game.ball.x );
        this.inPaddleX.setValue( Math.abs( this.x ) );

        // Debug Information
        System.out.println( this.inBias.getValue() + "\t\t\t" + this.inBallX.getValue() + "\t\t\t" + this.inPaddleX.getValue() );
        System.out.println( this.outMoveDirection.getValue() );
        System.out.println( "------" );
    }

    private void act() {
        if ( this.outMoveDirection.getValue() <= 0.5f ) {
            this.dx = -Player.MOVE_SPEED;
        } else {
            this.dx = Player.MOVE_SPEED;
        }
    }

    public void reset() {
        this.x = game.getWidth() / 2 - Player.PLAYER_WIDTH / 2;
        this.y = game.getHeight() - 20;

        for ( int i = 0; i < 3; i++ ) {
            this.brain.randomMutation( 0.5f );
        }
    }

}
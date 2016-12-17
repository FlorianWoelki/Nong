package com.florianwoelki.nong.neuralnetwork;

import com.florianwoelki.nong.math.MathUtil;
import com.florianwoelki.nong.neuralnetwork.exception.NNNotFullyMeshedException;
import com.florianwoelki.nong.neuralnetwork.exception.NotSameAmountOfNeuronsException;
import com.florianwoelki.nong.neuralnetwork.neuron.Connection;
import com.florianwoelki.nong.neuralnetwork.neuron.InputNeuron;
import com.florianwoelki.nong.neuralnetwork.neuron.Neuron;
import com.florianwoelki.nong.neuralnetwork.neuron.WorkingNeuron;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Woelki on 15.12.16.
 */
public class NeuralNetwork {

    private final float NEURON_SIZE = 15;

    private boolean isFullyMeshedGenerated;

    private List<InputNeuron> inputNeurons = new ArrayList<>();
    private List<WorkingNeuron> hiddenNeurons = new ArrayList<>();
    private List<WorkingNeuron> outputNeurons = new ArrayList<>();

    public void addInputNeuron( InputNeuron inputNeuron ) {
        this.inputNeurons.add( inputNeuron );
    }

    public void addHiddenNeuron( WorkingNeuron workingNeuron ) {
        this.hiddenNeurons.add( workingNeuron );
    }

    public void addOutputNeuron( WorkingNeuron workingNeuron ) {
        this.outputNeurons.add( workingNeuron );
    }

    public void generateHiddenNeurons( int amount ) {
        for ( int i = 0; i < amount; i++ ) {
            this.hiddenNeurons.add( new WorkingNeuron() );
        }
    }

    public void invalidate() {
        for ( WorkingNeuron wn : this.hiddenNeurons ) {
            wn.invalidate();
        }

        for ( WorkingNeuron wn : this.outputNeurons ) {
            wn.invalidate();
        }
    }

    public void randomizeAllWeights() {
        for ( WorkingNeuron hiddenNeuron : this.hiddenNeurons ) {
            hiddenNeuron.randomizeWeights();
        }

        for ( WorkingNeuron outputNeuron : this.outputNeurons ) {
            outputNeuron.randomizeWeights();
        }
    }

    public void generateFullMesh() {
        this.isFullyMeshedGenerated = true;

        for ( WorkingNeuron hiddenNeuron : this.hiddenNeurons ) {
            for ( InputNeuron inputNeuron : this.inputNeurons ) {
                hiddenNeuron.addNeuronConnection( inputNeuron, 1 );
            }
        }

        for ( WorkingNeuron outputNeuron : this.outputNeurons ) {
            for ( WorkingNeuron hiddenNeuron : this.hiddenNeurons ) {
                outputNeuron.addNeuronConnection( hiddenNeuron, 1 );
            }
        }
    }

    public void randomMutation( float mutationRate ) {
        int index = MathUtil.random.nextInt( this.hiddenNeurons.size() + this.outputNeurons.size() );
        if ( index < this.hiddenNeurons.size() ) {
            this.hiddenNeurons.get( index ).randomMutation( mutationRate );
        } else {
            this.outputNeurons.get( index - this.hiddenNeurons.size() ).randomMutation( mutationRate );
        }
    }

    public InputNeuron getInputNeuronFromIndex( int index ) {
        return this.inputNeurons.get( index );
    }

    public InputNeuron getInputNeuronFromName( String name ) {
        for ( InputNeuron neuron : this.inputNeurons ) {
            if ( name.equals( neuron.getName() ) ) {
                return neuron;
            }
        }
        return null;
    }

    public WorkingNeuron getHiddenNeuronFromIndex( int index ) {
        return this.hiddenNeurons.get( index );
    }

    public WorkingNeuron getHiddenNeuronFromName( String name ) {
        for ( WorkingNeuron neuron : this.hiddenNeurons ) {
            if ( name.equals( neuron.getName() ) ) {
                return neuron;
            }
        }
        return null;
    }

    public WorkingNeuron getOutputNeuronFromIndex( int index ) {
        return this.outputNeurons.get( index );
    }

    public WorkingNeuron getOutputNeuronFromName( String name ) {
        for ( WorkingNeuron neuron : this.outputNeurons ) {
            if ( name.equals( neuron.getName() ) ) {
                return neuron;
            }
        }
        return null;
    }

    public NeuralNetwork cloneFullMesh() throws NNNotFullyMeshedException, NotSameAmountOfNeuronsException {
        if ( !this.isFullyMeshedGenerated ) {
            throw new NNNotFullyMeshedException( "The Neural Network is not fully meshed generated." );
        }

        NeuralNetwork copy = new NeuralNetwork();

        for ( InputNeuron inputNeuron : this.inputNeurons ) {
            copy.addInputNeuron( (InputNeuron) inputNeuron.nameCopy() );
        }

        for ( WorkingNeuron hiddenNeuron : this.hiddenNeurons ) {
            copy.addHiddenNeuron( (WorkingNeuron) hiddenNeuron.nameCopy() );
        }

        for ( WorkingNeuron outputNeuron : this.outputNeurons ) {
            copy.addOutputNeuron( (WorkingNeuron) outputNeuron.nameCopy() );
        }

        copy.generateFullMesh();

        for ( int i = 0; i < this.hiddenNeurons.size(); i++ ) {
            List<Connection> connectionsOriginal = this.hiddenNeurons.get( i ).getConnections();
            List<Connection> connectionsCopy = copy.hiddenNeurons.get( i ).getConnections();
            if ( connectionsOriginal.size() != connectionsCopy.size() ) {
                throw new NotSameAmountOfNeuronsException( "Cloning the hidden neurons was not successful. Because both has not the same size." );
            }

            for ( int k = 0; k < connectionsOriginal.size(); k++ ) {
                connectionsCopy.get( k ).weight = connectionsOriginal.get( k ).weight;
            }
        }

        for ( int i = 0; i < this.outputNeurons.size(); i++ ) {
            List<Connection> connectionsOriginal = this.outputNeurons.get( i ).getConnections();
            List<Connection> connectionsCopy = copy.outputNeurons.get( i ).getConnections();
            if ( connectionsOriginal.size() != connectionsCopy.size() ) {
                throw new NotSameAmountOfNeuronsException( "Cloning the hidden neurons was not successful. Because both has not the same size." );
            }

            for ( int k = 0; k < connectionsOriginal.size(); k++ ) {
                connectionsCopy.get( k ).weight = connectionsOriginal.get( k ).weight;
            }
        }

        return copy;
    }

    public void render( Graphics g, Rectangle rect ) {
        this.calculateNeuronsRenderPosition( rect );
        float strongestConnection = this.getStrongestConnection();
        renderLayer( g, this.outputNeurons, strongestConnection, 10, 10 );
        renderLayer( g, this.hiddenNeurons, strongestConnection );
        renderLayerI( g, this.inputNeurons, strongestConnection, -120, 10, true );
    }

    private void renderLayerI( Graphics g, List<InputNeuron> layer, float strongestConnection, int nameOffsetX, int nameOffsetY, boolean writeRight ) {
        for ( InputNeuron aLayer : layer ) {
            this.drawNeuron( g, aLayer, strongestConnection, nameOffsetX, nameOffsetY, writeRight );
        }
    }

    private void renderLayer( Graphics g, List<WorkingNeuron> layer, float strongestConnection, int nameOffsetX, int nameOffsetY ) {
        for ( WorkingNeuron aLayer : layer ) {
            this.drawNeuron( g, aLayer, strongestConnection, nameOffsetX, nameOffsetY, false );
        }
    }

    private void renderLayer( Graphics g, List<WorkingNeuron> layer, float strongestConnection ) {
        for ( WorkingNeuron aLayer : layer ) {
            this.drawNeuron( g, aLayer, strongestConnection, 0, 0, false );
        }
    }

    private void drawNeuron( Graphics g, Neuron n, float strongestConnection, int nameOffsetX, int nameOffsetY, boolean writeRight ) {
        if ( n instanceof WorkingNeuron ) {
            this.drawConnections( g, n, strongestConnection );
        }

        float x = n.drawX;
        float y = n.drawY;
        Color c;
        float val = n.getValue();
        if ( val < 0 ) {
            c = Color.RED;
        } else {
            c = Color.GREEN;
        }

        float valSize = val * this.NEURON_SIZE;

        g.setColor( Color.WHITE );
        g.fillOval( (int) x, (int) y, (int) this.NEURON_SIZE / 2 + 1, (int) this.NEURON_SIZE / 2 + 1 );
        g.setColor( c );

        if ( nameOffsetX != 0 || nameOffsetY != 0 ) {
            float xPos = x + nameOffsetX;
            float yPos = y + nameOffsetY;
            if ( writeRight ) {
                xPos -= 10;
            }
            g.setColor( Color.WHITE );
            g.drawString( n.getName(), (int) xPos, (int) yPos );
        }
    }

    private void drawConnections( Graphics g, Neuron n, float strongestConnection ) {
        WorkingNeuron wn = (WorkingNeuron) n;
        for ( Connection connection : wn.getConnections() ) {
            Color color;
            float value = connection.getValue();
            float alpha = Math.abs( value ) / strongestConnection;
            if ( alpha > 1f ) {
                alpha = 1f;
            }
            if ( value > 0 ) {
                color = new Color( 0f, 1f, 0f, alpha );
            } else {
                color = new Color( 1f, 0f, 0f, alpha );
            }

            g.setColor( color );
            g.drawLine( (int) n.drawX, (int) n.drawY, (int) connection.entryNeuron.drawX, (int) connection.entryNeuron.drawY );
        }
    }

    private void calculateNeuronsRenderPosition( Rectangle rectangle ) {
        float yMin = rectangle.y + this.NEURON_SIZE / 2;
        float yMax = rectangle.y + rectangle.height - this.NEURON_SIZE / 2;
        this.calculateNeuronsRenderPositionLayer( this.outputNeurons, rectangle.x + rectangle.width - this.NEURON_SIZE / 2, yMin, yMax );
        this.calculateNeuronsRenderPositionLayer( this.hiddenNeurons, rectangle.x + rectangle.width / 2, yMin, yMax );
        this.calculateNeuronsRenderPositionLayerI( this.inputNeurons, rectangle.x + this.NEURON_SIZE / 2, yMin, yMax );
    }

    private void calculateNeuronsRenderPositionLayerI( List<InputNeuron> layer, float x, float yMin, float yMax ) {
        float yDiff = yMax - yMin;
        float distanceBetweenNeurons = yDiff / ( layer.size() - 1 );
        float currentY = yMin;
        for ( InputNeuron aLayer : layer ) {
            aLayer.drawX = x;
            aLayer.drawY = currentY;
            currentY += distanceBetweenNeurons;
        }
    }

    private void calculateNeuronsRenderPositionLayer( List<WorkingNeuron> layer, float x, float yMin, float yMax ) {
        float yDiff = yMax - yMin;
        float distanceBetweenNeurons = yDiff / ( layer.size() - 1 );
        float currentY = yMin;
        for ( WorkingNeuron aLayer : layer ) {
            aLayer.drawX = x;
            aLayer.drawY = currentY;
            currentY += distanceBetweenNeurons;
        }
    }

    private float getStrongestConnection() {
        return MathUtil.max( this.getStrongestLayerConnection( this.hiddenNeurons ), this.getStrongestLayerConnection( this.outputNeurons ) );
    }

    private float getStrongestLayerConnection( List<WorkingNeuron> layer ) {
        float strongestConnection = 0;
        for ( Neuron n : layer ) {
            WorkingNeuron wn = (WorkingNeuron) n;
            float strongestNeuronConnection = Math.abs( wn.getStrongestConnection() );
            if ( strongestNeuronConnection > strongestConnection ) {
                strongestConnection = strongestNeuronConnection;
            }
        }
        return strongestConnection;
    }

}

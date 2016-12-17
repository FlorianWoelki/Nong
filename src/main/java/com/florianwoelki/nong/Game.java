package com.florianwoelki.nong;

import com.florianwoelki.nong.entity.Ball;
import com.florianwoelki.nong.entity.Player;
import com.florianwoelki.nong.input.Keyboard;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents the basic main game.
 * </summary>
 */
public class Game extends Canvas implements Runnable {

    private Window window;
    @Getter
    private Keyboard keyboard;

    private Thread thread;
    private boolean running;

    private Player player;
    @Getter
    private Ball ball;

    @Setter
    @Getter
    private int score;

    /**
     * Constructor of the game.
     * Initialize everything.
     */
    public Game() {
        this.keyboard = new Keyboard();

        this.requestFocus();
        this.setFocusable( true );
        this.addKeyListener( this.keyboard );

        this.window = new Window( this );
        this.window.setVisible( true );

        this.player = new Player( this );
        this.ball = new Ball( this, this.getWidth() / 2, this.getHeight() / 2 );
        this.ball.setSpeed( (float) Math.toRadians( -45 ) );
    }

    /**
     * Start the game thread.
     */
    public synchronized void start() {
        if ( this.running ) {
            return;
        }

        this.running = true;
        this.thread = new Thread( this, "Game Window" );
        this.thread.start();
    }

    /**
     * Stop the game thread.
     */
    public synchronized void stop() {
        if ( !this.running ) {
            return;
        }

        this.running = false;
        try {
            this.thread.join();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    /**
     * Run method of the thread.
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0;
        double ns = 1000000000d / 60d;
        long lastTimer = System.currentTimeMillis();

        int fps = 0, ups = 0;

        // Game Loop
        while ( this.running ) {
            boolean shouldRender = false;
            long now = System.nanoTime();
            delta += ( now - lastTime ) / ns;
            lastTime = now;
            while ( delta >= 1 ) {
                delta--;
                this.update();
                ups++;
                shouldRender = true;
            }

            try {
                Thread.sleep( 3 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }

            if ( shouldRender ) {
                this.render();
                fps++;
            }

            if ( System.currentTimeMillis() - lastTimer > 1000 ) {
                lastTimer += 1000;
                System.out.println( "FPS: " + fps + ", UPS: " + ups );
                ups = fps = 0;
            }
        }

        this.stop();
    }

    /**
     * This method updates the game with all components.
     */
    private void update() {
        this.keyboard.update();
        this.player.update();
        this.ball.update();
        this.ball.checkCollision( this.player );
    }

    /**
     * This method renders the game with all components.
     */
    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if ( bs == null ) {
            this.createBufferStrategy( 3 );
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor( Color.BLACK );
        g.fillRect( 0, 0, this.getWidth(), this.getHeight() );
        this.player.render( g );
        this.ball.render( g );
        g.drawString( "" + this.score, this.getWidth() / 2, 80 );
        // this.player.brain.render( g, new Rectangle( 150, 0, 200, 250 ) );
        g.dispose();
        bs.show();
    }

}

package com.florianwoelki.nong.entity;

import com.florianwoelki.nong.Game;

import java.awt.*;

/**
 * Created by Florian Woelki on 16.12.16.
 * <p>
 * <summary>
 * This class represents the Ball of the game.
 * </summary>
 */
public class Ball extends Entity {

    private final int SPEED = 5;

    /**
     * The constructor of the ball.
     *
     * @param game Main Game class
     * @param x    X coordinate of the ball, where it will spawn
     * @param y    Y coordinate of the ball, where it will spawn
     */
    public Ball( Game game, float x, float y ) {
        super( game, x, y );

        this.width = 20;
        this.height = 20;
    }

    /**
     * This method renders the ball.
     *
     * @param g Graphics for rendering
     */
    @Override
    public void render( Graphics g ) {
        g.setColor( Color.WHITE );
        g.fillRect( (int) this.x, (int) this.y, this.width, this.height );
    }

    /**
     * This method updates the ball.
     * It handles the collision with the Window.
     */
    @Override
    public void update() {
        this.oldX = this.x;
        this.oldY = this.y;

        this.x += this.dx;
        this.y += this.dy;

        // Handle collision with game window
        if ( this.y < 0 ) {
            this.y = 0;
            this.invertYSpeed();
        }
        if ( this.x < 0 ) {
            this.x = 0;
            this.invertXSpeed();
        } else if ( this.x > this.game.getWidth() - this.width ) {
            this.x = this.game.getWidth() - this.width;
            this.invertXSpeed();
        }
    }

    /**
     * Set the speed of the ball.
     * Sets the dx and dy.
     *
     * @param rotation Rotation in radians of speed
     */
    public void setSpeed( float rotation ) {
        this.dx = (float) ( Math.cos( rotation ) * this.SPEED );
        this.dy = (float) ( Math.sin( rotation ) * this.SPEED );
    }

    /**
     * Checks the collision between the ball and the player.
     *
     * @param player The player who is interacting with the ball
     */
    public void checkCollision( Player player ) {
        // Checks if ball interacts with player
        if ( this.x <= player.x + player.width && this.x + this.width >= player.x &&
                this.y <= player.y + player.height && this.y + this.height >= player.y ) {
            if ( this.oldX <= player.x + player.width && this.oldX + this.width >= player.x &&
                    this.y <= player.y + player.height && this.y + this.height >= player.y ) {
                if ( this.oldY >= player.y + player.height / 2f ) {
                    this.y = player.y + player.height + 0.001f;
                } else {
                    this.y = player.y - this.height - 0.001f;
                }
            }

            if ( this.x <= player.x + player.width && this.x + this.width >= player.x &&
                    this.oldY <= player.y + player.height && this.oldY + this.height >= player.y ) {
                if ( this.oldX >= player.x + player.width / 2f ) {
                    this.x = player.x + player.width + 0.001f;
                } else {
                    this.x = player.x - this.width - 0.001f;
                }
            }

            this.invertYSpeed();
            this.game.setScore( this.game.getScore() + 1 );
        }

        // Check if resetting is needed
        if ( this.y > this.game.getHeight() - this.height ) {
            this.game.setScore( 0 );
            this.reset();
            player.reset();
        }
    }

    /**
     * This method resets the coordinates and the speed of the ball.
     */
    public void reset() {
        this.x = this.game.getWidth() / 2;
        this.y = this.game.getHeight() / 2;
        this.setSpeed( (float) Math.toRadians( -45 ) );
    }

    /**
     * This method inverts the x speed.
     */
    private void invertXSpeed() {
        this.dx *= -1;
    }

    /**
     * This method inverts the y speed.
     */
    private void invertYSpeed() {
        this.dy *= -1;
    }

}

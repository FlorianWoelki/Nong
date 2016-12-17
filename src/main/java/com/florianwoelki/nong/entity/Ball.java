package com.florianwoelki.nong.entity;

import com.florianwoelki.nong.Game;

import java.awt.*;

/**
 * Created by Florian Woelki on 16.12.16.
 */
public class Ball extends Entity {

    private final int SPEED = 5;

    public Ball( Game game, float x, float y ) {
        super( game, x, y );

        this.width = 20;
        this.height = 20;
    }

    @Override
    public void render( Graphics g ) {
        g.setColor( Color.WHITE );
        g.fillRect( (int) this.x, (int) this.y, this.width, this.height );
    }

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

    public void setSpeed( float rotation ) {
        this.dx = (float) ( Math.cos( rotation ) * this.SPEED );
        this.dy = (float) ( Math.sin( rotation ) * this.SPEED );
    }

    public void checkCollision( Player e ) {
        if ( this.x <= e.x + e.width && this.x + this.width >= e.x &&
                this.y <= e.y + e.height && this.y + this.height >= e.y ) {
            if ( this.oldX <= e.x + e.width && this.oldX + this.width >= e.x &&
                    this.y <= e.y + e.height && this.y + this.height >= e.y ) {
                if ( this.oldY >= e.y + e.height / 2f ) {
                    this.y = e.y + e.height + 0.001f;
                } else {
                    this.y = e.y - this.height - 0.001f;
                }
            }

            if ( this.x <= e.x + e.width && this.x + this.width >= e.x &&
                    this.oldY <= e.y + e.height && this.oldY + this.height >= e.y ) {
                if ( this.oldX >= e.x + e.width / 2f ) {
                    this.x = e.x + e.width + 0.001f;
                } else {
                    this.x = e.x - this.width - 0.001f;
                }
            }

            this.invertYSpeed();
            this.game.score++;
        }

        if ( this.y > this.game.getHeight() - this.height ) {
            this.game.score = 0;
            this.reset();
            e.reset();
        }
    }

    public void reset() {
        this.x = this.game.getWidth() / 2;
        this.y = this.game.getHeight() / 2;
        this.setSpeed( (float) Math.toRadians( -45 ) );
    }

    private void invertXSpeed() {
        this.dx *= -1;
    }

    private void invertYSpeed() {
        this.dy *= -1;
    }

}

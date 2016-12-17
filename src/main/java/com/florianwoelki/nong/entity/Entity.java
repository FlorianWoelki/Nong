package com.florianwoelki.nong.entity;

import com.florianwoelki.nong.Game;

import java.awt.*;

/**
 * Created by Florian Woelki on 15.12.16.
 */
public abstract class Entity {

    protected Game game;

    protected float x;
    protected float y;

    protected int width;
    protected int height;

    protected float dx;
    protected float dy;

    protected float oldX;
    protected float oldY;

    public Entity( Game game, float x, float y ) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public abstract void render( Graphics g );

    public abstract void update();

}

package com.florianwoelki.nong.entity;

import com.florianwoelki.nong.Game;

import java.awt.*;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents a basic entity with all the properties.
 * </summary>
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

    /**
     * Basic entity constructor for all entities.
     *
     * @param game Main Game class
     * @param x    X coordinate of the entity, where it will spawn
     * @param y    Y coordinate of the entity, where it will spawn
     */
    public Entity( Game game, float x, float y ) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    /**
     * With this method you can render the entity.
     *
     * @param g Graphics for rendering
     */
    public abstract void render( Graphics g );

    /**
     * With this method you can update the entity.
     */
    public abstract void update();

}

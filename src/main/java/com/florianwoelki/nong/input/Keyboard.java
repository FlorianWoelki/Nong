package com.florianwoelki.nong.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents the keyboard.
 * It handles all the input of your keyboard.
 * </summary>
 */
public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[120];
    public boolean left, right;
    public boolean isSpaceActivated;

    /**
     * Update the keys of the game.
     */
    public void update() {
        this.left = this.keys[KeyEvent.VK_LEFT] || this.keys[KeyEvent.VK_A];
        this.right = this.keys[KeyEvent.VK_RIGHT] || this.keys[KeyEvent.VK_D];
    }

    @Override
    public void keyPressed( KeyEvent e ) {
        if ( e.getKeyCode() < this.keys.length ) {
            if ( e.getKeyCode() == KeyEvent.VK_SPACE ) {
                this.isSpaceActivated = !this.isSpaceActivated;
                return;
            }

            this.keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased( KeyEvent e ) {
        if ( e.getKeyCode() < this.keys.length && e.getKeyCode() != KeyEvent.VK_SPACE ) {
            this.keys[e.getKeyCode()] = false;
        }
    }

    @Override
    public void keyTyped( KeyEvent e ) {
    }

}

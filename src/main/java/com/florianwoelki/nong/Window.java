package com.florianwoelki.nong;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents the window.
 * </summary>
 */
public class Window extends JFrame {

    private static final int WIDTH = 480;
    private static final int HEIGHT = 640;
    private static final String TITLE = "Nong";

    /**
     * Constructor of the window.
     * This will construct the basic game window.
     *
     * @param game Main Game class
     */
    public Window(Game game) {
        super(Window.TITLE);

        Dimension size = new Dimension(Window.WIDTH, Window.HEIGHT);
        game.setPreferredSize(size);
        game.setMaximumSize(size);
        game.setMinimumSize(size);

        this.add(game);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

}


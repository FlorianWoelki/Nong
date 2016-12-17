package com.florianwoelki.nong;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Florian Woelki on 15.12.16.
 */
public class Window extends JFrame {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 640;
    public static final String TITLE = "Nong";

    public Window( Game game ) {
        super( Window.TITLE );

        Dimension size = new Dimension( Window.WIDTH, Window.HEIGHT );
        game.setPreferredSize( size );
        game.setMaximumSize( size );
        game.setMinimumSize( size );

        this.add( game );
        this.pack();
        this.setResizable( false );
        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        this.setLocationRelativeTo( null );
    }

}


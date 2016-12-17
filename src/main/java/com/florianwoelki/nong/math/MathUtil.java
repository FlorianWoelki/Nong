package com.florianwoelki.nong.math;

import java.util.Random;

/**
 * Created by Florian Woelki on 15.12.16.
 */
public final class MathUtil {

    public static final float PI = 3.1415926535f;
    public static final Random random = new Random();

    public static float abs( float value ) {
        return Math.abs( value );
    }

    public static float sigmoid( float x ) {
        return (float) ( 1 / ( 1 + Math.exp( -x ) ) );
    }

    public static float max( float a, float b ) {
        return a > b ? a : b;
    }

}

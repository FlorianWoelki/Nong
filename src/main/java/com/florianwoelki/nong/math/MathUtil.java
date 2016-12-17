package com.florianwoelki.nong.math;

import java.util.Random;

/**
 * Created by Florian Woelki on 15.12.16.
 */
public final class MathUtil {

    public static final float PI = 3.1415926535f;
    public static final float DEGREE_TO_RADIANS = PI / 180.0f;
    public static final float RADIANS_TO_DEGREE = 180.0f / PI;
    public static final Random random = new Random();

    public static float abs( float value ) {
        return Math.abs( value );
    }

    public static float sin( float value ) {
        return (float) Math.sin( value );
    }

    public static float cos( float value ) {
        return (float) Math.cos( value );
    }

    public static float tan( float value ) {
        return (float) Math.tan( value );
    }

    public static float asin( float value ) {
        return (float) Math.asin( value );
    }

    public static float acos( float value ) {
        return (float) Math.acos( value );
    }

    public static float atan( float value ) {
        return (float) Math.atan( value );
    }

    public static float sigmoid( float x ) {
        return (float) ( 1 / ( 1 + Math.exp( -x ) ) );
    }

    public static int clampColorValue( int val ) {
        if ( val < 0 ) return 0;
        if ( val > 255 ) return 255;
        return val;
    }

    public static float clamp( float value ) {
        if ( value < 0 ) return 0;
        if ( value > 1 ) return 1;
        return value;
    }

    public static float max( float a, float b ) {
        return a > b ? a : b;
    }

    public static float clampNegativePosition( float value ) {
        if ( value < -1 ) return -1;
        if ( value > 1 ) return 1;
        return value;
    }

}

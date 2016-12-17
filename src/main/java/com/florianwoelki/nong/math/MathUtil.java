package com.florianwoelki.nong.math;

import java.util.Random;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents all the math functions we need.
 * </summary>
 */
public final class MathUtil {

    public static final Random random = new Random();

    /**
     * Get the absolute value.
     *
     * @param value Given value which will be absolute
     * @return Absolute value of the given value
     */
    public static float abs( float value ) {
        return Math.abs( value );
    }

    /**
     * Calculate the basic sigmoid function.
     *
     * @param x Given value for the sigmoid function
     * @return Result of the calculation of the sigmoid function
     */
    public static float sigmoid( float x ) {
        return (float) ( 1 / ( 1 + Math.exp( -x ) ) );
    }

    /**
     * Compares number one and two which one is bigger.
     *
     * @param a Number one
     * @param b Number two
     * @return The max value
     */
    public static float max( float a, float b ) {
        return a > b ? a : b;
    }

}

package com.florianwoelki.nong.neuralnetwork.exception;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents the exception if the neurons has not the same amount.
 * </summary>
 */
public class NotSameAmountOfNeuronsException extends Exception {

    /**
     * Constructor of the exception
     *
     * @param message Which will be printed if the error occurs.
     */
    public NotSameAmountOfNeuronsException(String message) {
        super(message);
    }

}

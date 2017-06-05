package com.florianwoelki.nong.neuralnetwork.exception;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents the exception if the neural network is not fully meshed.
 * </summary>
 */
public class NNNotFullyMeshedException extends Exception {

    /**
     * Constructor of the exception
     *
     * @param message Which will be printed if the error occurs.
     */
    public NNNotFullyMeshedException(String message) {
        super(message);
    }

}

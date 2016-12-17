package com.florianwoelki.nong.neuralnetwork.neuron;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents a basic neuron of the neural network.
 * </summary>
 */
public abstract class Neuron {

    @Setter
    @Getter
    private String name = "no name";

    public float drawX;
    public float drawY;

    /**
     * Get the value of the neuron.
     *
     * @return The value of the neuron
     */
    public abstract float getValue();

    /**
     * Copies the name of the neuron.
     *
     * @return The copied name of the neuron
     */
    public abstract Neuron nameCopy();

}

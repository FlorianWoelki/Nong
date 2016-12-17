package com.florianwoelki.nong.neuralnetwork.neuron;

import lombok.Setter;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents the input neurons of the neural network.
 * </summary>
 */
public class InputNeuron extends Neuron {

    @Setter
    private Float value;

    /**
     * Get the value of the neuron.
     *
     * @return The value of the neuron
     */
    @Override
    public float getValue() {
        if ( this.value == null ) {
            return 0f;
        }

        return this.value;
    }

    /**
     * Copies the name of the neuron.
     *
     * @return The copied name of the neuron
     */
    @Override
    public Neuron nameCopy() {
        InputNeuron clone = new InputNeuron();
        clone.setName( this.getName() );
        return clone;
    }

}

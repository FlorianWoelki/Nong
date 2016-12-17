package com.florianwoelki.nong.neuralnetwork.neuron;

import lombok.AllArgsConstructor;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents the connection between the neurons.
 * </summary>
 */
@AllArgsConstructor
public class Connection {

    public Neuron entryNeuron;
    public float weight = 1;

    /**
     * Get the value of this connection.
     *
     * @return The calculated value
     */
    public float getValue() {
        return this.weight * this.entryNeuron.getValue();
    }

}

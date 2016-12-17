package com.florianwoelki.nong.neuralnetwork.neuron;

/**
 * Created by Florian Woelki on 15.12.16.
 */
public class Connection {

    public float weight = 1;
    public Neuron entryNeuron;

    public Connection( Neuron entryNeuron, float weight ) {
        this.weight = weight;
        this.entryNeuron = entryNeuron;
    }

    public float getValue() {
        return this.weight * this.entryNeuron.getValue();
    }

}

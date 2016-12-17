package com.florianwoelki.nong.neuralnetwork.neuron;

/**
 * Created by Florian Woelki on 15.12.16.
 */
public class InputNeuron extends Neuron {

    private Float value;

    public void setValue( Float value ) {
        this.value = value;
    }

    @Override
    public float getValue() {
        if ( this.value == null ) {
            return 0f;
        }

        return this.value;
    }

    @Override
    public Neuron nameCopy() {
        InputNeuron clone = new InputNeuron();
        clone.setName( this.getName() );
        return clone;
    }

}

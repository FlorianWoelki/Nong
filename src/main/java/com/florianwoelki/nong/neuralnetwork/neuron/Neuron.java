package com.florianwoelki.nong.neuralnetwork.neuron;

/**
 * Created by Florian Woelki on 15.12.16.
 */
public abstract class Neuron {

    private String name = "no name";

    public float drawX;
    public float drawY;

    public abstract float getValue();

    public abstract Neuron nameCopy();

    public String getName() {
        return this.name;
    }

    public void setName( String name ) {
        this.name = name;
    }

}

package com.florianwoelki.nong.neuralnetwork.neuron;

import com.florianwoelki.nong.math.MathUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Woelki on 15.12.16.
 * <p>
 * <summary>
 * This class represents a hidden or a output neuron.
 * </summary>
 */
public class WorkingNeuron extends Neuron {

    private float value = 0.0f;
    @Getter
    private List<Connection> connections = new ArrayList<>();

    /**
     * This method gives a random mutation of a random connection.
     *
     * @param mutationRate The mutation rate
     */
    public void randomMutation(float mutationRate) {
        Connection connection = this.connections.get(MathUtil.random.nextInt(this.connections.size()));
        connection.weight += (float) MathUtil.random.nextDouble() * 2 * mutationRate - mutationRate;
    }

    /**
     * Add a neuron connection.
     *
     * @param neuron Previous neuron
     * @param weight Given weight between both neurons
     */
    public void addNeuronConnection(Neuron neuron, float weight) {
        this.addNeuronConnection(new Connection(neuron, weight));
    }

    /**
     * Add a neuron connection.
     *
     * @param connection Connection between neurons
     */
    public void addNeuronConnection(Connection connection) {
        this.connections.add(connection);
    }

    /**
     * This method randomize all the weights between the neurons.
     */
    public void randomizeWeights() {
        for(Connection connection : this.connections) {
            connection.weight = (float) (MathUtil.random.nextDouble() * 2 - 1);
        }
    }

    /**
     * Calculate the value of the working neuron.
     */
    private void calculate() {
        float value = 0;
        for(Connection connection : this.connections) {
            value += connection.getValue();
        }

        value = MathUtil.sigmoid(value);
        this.value = value;
    }

    /**
     * Get the value of the neuron.
     *
     * @return The value of the neuron
     */
    @Override
    public float getValue() {
        if(this.value == 0.0f) {
            calculate();
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
        WorkingNeuron clone = new WorkingNeuron();
        clone.setName(getName());
        return clone;
    }

    /**
     * Get the strongest connection of all connections.
     *
     * @return The strongest connection
     */
    public float getStrongestConnection() {
        float strongest = 0;
        for(Connection connection : this.connections) {
            float val = MathUtil.abs(connection.weight);
            if(val > strongest) {
                strongest = val;
            }
        }
        return strongest;
    }

}

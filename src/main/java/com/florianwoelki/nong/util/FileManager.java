package com.florianwoelki.nong.util;

import com.florianwoelki.nong.neuralnetwork.NeuralNetwork;

import java.io.*;

/**
 * Created by Florian Woelki on 04.06.17.
 * <p>
 * <summary>
 * This class will save the current neural network and it will
 * load the neural network which will be saved mostly automatically.
 * </summary>
 */
public final class FileManager {

    /**
     * This method will save the neural network.
     *
     * @param neuralNetwork current neural network
     * @param path where the neural network will be stored
     */
    public static void save(NeuralNetwork neuralNetwork, String path) {
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(neuralNetwork);
            objectOutputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will load a neural network by the path.
     *
     * @param path where the neural network is stored
     * @return the neural network which will be loaded
     */
    public static NeuralNetwork load(String path) {
        NeuralNetwork neuralNetwork = null;

        try {
            FileInputStream inputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            neuralNetwork = (NeuralNetwork) objectInputStream.readObject();
            objectInputStream.close();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return neuralNetwork;
    }

    /**
     * This method checks if the file in the path exists.
     *
     * @param path where the file is
     * @return boolean if the file exists
     */
    public static boolean exist(String path) {
        File file = new File(path);
        return file.exists();
    }

}

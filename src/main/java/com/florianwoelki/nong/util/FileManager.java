package com.florianwoelki.nong.util;

import com.florianwoelki.nong.neuralnetwork.NeuralNetwork;

import java.io.*;

/**
 * Created by Florian Woelki on 04.06.17.
 */
public final class FileManager {

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

    public static boolean exist(String path) {
        File file = new File(path);
        return file.exists();
    }

}

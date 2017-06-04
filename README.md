# Nong

Nong is a pong game which is played by a neural network. You can see everything live.
This project has no real liscence. Do whatever you want with it.

![alt tag](https://raw.githubusercontent.com/SgtFloW/Nong/master/src/main/resources/picture01.png)

## Controls

The controls are simple:
* space to control the paddle, space again to train the neural network
* if space activated, left & right keys to control the paddle

## Neural Network

This neural network uses three input neurons, three hidden neurons and one output neuron.
Input neurons:
* Bias
* Ball X
* Paddle X

Output neuron:
* Out move direction (value <= 5 -> left, value > 5 -> right)

## Install

Just copy the project into your IDE and run the Pong.class. This is where the main method is.

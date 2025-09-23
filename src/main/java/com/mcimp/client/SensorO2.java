package com.mcimp.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class SensorO2 extends AbstractSensor {

    private final Random random = new Random();

    @Override
    public String getType() {
        return "O2";
    }

    @Override
    public double generateValue() {
        return 15 + random.nextDouble() * 10; // 15–25 %
    }

    public static void main(String[] args) {
        new Thread(new SensorO2()).start();
    }
}
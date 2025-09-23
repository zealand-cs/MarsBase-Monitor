package com.mcimp.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class SensorCO2 extends AbstractSensor{

    private final Random random = new Random();

    @Override
    public String getType() {
        return "CO2";
    }

    @Override
    public double generateValue() {
        return 1000 + random.nextInt(2000); // 1000–3000 ppm
    }

    public static void main(String[] args) {
        new Thread(new SensorCO2()).start();
    }
}

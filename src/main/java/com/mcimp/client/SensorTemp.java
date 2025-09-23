package com.mcimp.client;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class SensorTemp extends AbstractSensor {

    private final Random random = new Random();

    @Override
    public String getType() {
        return "TEMP";
    }

    @Override
    public double generateValue() {
        return 35 + random.nextDouble() * 5; // 35–40 °C
    }

    public static void main(String[] args) {
        new Thread(new SensorTemp()).start();
    }
}

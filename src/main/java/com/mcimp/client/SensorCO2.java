package com.mcimp.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class SensorCO2 implements SensorInterface{

    private static final String HOST = "localhost";
    private static final int PORT = 5555;

    public static void main(String[] args) {
        Random random = new Random();

        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            while (true) {
                int value = 1000 + random.nextInt(2000); // 1000–3000 ppm
                out.println("CO2:" + value);
                Thread.sleep(5000);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("[ERROR] Sensor CO2 mistede forbindelsen.");
        }
    }
}

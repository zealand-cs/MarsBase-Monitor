
package com.mcimp.client;


import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Locale;

public abstract class AbstractSensor implements SensorInterface {
    private static final String HOST = "localhost";
    private static final int PORT = 5555;
    private static final int INTERVAL = 5000; // 5 sec

    @Override
    public void run() {
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println(getType() + " sensor connected to HQ");

            while (true) {
                double value = generateValue();

                out.println(getType() + ":" + String.format(Locale.US, "%.2f", value));
                Thread.sleep(INTERVAL);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("[ERROR] Sensor " + getType() + " mistede forbindelsen.");
        }
    }
}


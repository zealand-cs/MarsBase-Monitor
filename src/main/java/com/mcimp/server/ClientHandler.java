package com.mcimp.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientHandler implements Runnable {
    private static final Logger logger = LogManager.getLogger(ClientHandler.class);

    private final int timeout;
    private final Socket socket;

    public ClientHandler(Socket socket,  int timeout) {
        this.socket = socket;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            socket.setSoTimeout(timeout);

            try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    processLine(line, writer);
                }

            } catch (IOException e) {
                logger.error("ERROR, SENSOR LOST CONNECTION");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    logger.error("Could not close socket", e);
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private void processLine(String line, PrintWriter writer) {
        String[] parts = line.split(":");
        if (parts.length != 2) {
            logger.warn("Invalid sensorformat: {}", line);
            return;
        }

        String type = parts[0].trim();
        double value;

        try {
            String valueStr = parts[1].trim().replace(",", ".");
            value = Double.parseDouble(valueStr);
        } catch (NumberFormatException e) {
            logger.warn("Could not parse sensor value from sensor: {}", line);
            return;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logEntry = "[" + timestamp + "] " + type + ": " + value;

        boolean alarm = checkThreshold(type, value);
        if (alarm) {
            String alarmMsg = "ALARM: " + type + " value out of range! (value = " + value + ")";
            System.out.println(alarmMsg);
            writer.println(alarmMsg);
            logEntry += " -> ALARM!";
        }


        logger.info(logEntry);
    }

    private boolean checkThreshold(String type, double value) {
        switch (type.toUpperCase()) {
            case "TEMP":
                return (value < Server.TEMP_MIN || value > Server.TEMP_MAX);
            case "O2":
                return (value < Server.O2_MIN || value > Server.O2_MAX);
            case "PRESSURE":
                return (value < Server.PRESSURE_MIN || value > Server.PRESSURE_MAX);
            case "CO2":
                return (value > Server.CO2_MAX);
            default:
                logger.warn("Unknown SensorType: {}", type);
                return false;
        }

    }
}

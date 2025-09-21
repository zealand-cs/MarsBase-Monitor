/*
package com.mcimp.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.mcimp.utils.SensorPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sensor {
    private static final Logger logger = LogManager.getLogger(Sensor.class);

    private String hostname;
    private int port;
    private int timeout;

    public String name;

    public Sensor(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(hostname, port), timeout);
            socket.setSoTimeout(timeout);

            // Auto-flush enabled even though it's probably not necessary
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                Thread.sleep(5000);

                SensorPacket

                writer.flush();
            }
        } catch (SocketTimeoutException e) {
            logger.error("socket connection to " + hostname + ":" + port + " timed out: " + e);
        } catch (IOException e) {
            logger.error("unknown IO Exception occoured: " + e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Sensor sensor = new Sensor("127.0.0.1", 2244);
        sensor.start();
    }
}
*/

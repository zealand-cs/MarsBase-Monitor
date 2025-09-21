package com.mcimp.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final Logger logger = LogManager.getLogger(ClientHandler.class);

    public static final double TEMP_MIN = -15.0;
    public static final double TEMP_MAX = 35.0;
    public static final double O2_MIN = 19.0;
    public static final double O2_MAX = 23.0;
    public static final double PRESSURE_MIN = 800.0;
    public static final double PRESSURE_MAX = 1100.0;
    public static final double CO2_MAX = 2000.0;

    private final ExecutorService pool;
    private volatile boolean running = false;

    public Server(int threads) {
        pool = Executors.newFixedThreadPool(threads);
    }


    public void stop() {
        running = false;
        pool.shutdown();
    }

    public void startServer() {
        running = true;
        // Try with resources automatically closes the ServerSocket if an exception
        // occurs
        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            System.out.println("Server is listening on port 5555");

            // Necessary to handle multiple clients at the same time
            while (running) {
                Socket clientSocket = serverSocket.accept();
                /*
                 * Creates a new thread to handle clients seperately
                 * ClientHandler implements Runnable
                 */
                pool.execute(new ClientHandler(clientSocket, 5*60*1000));
            }
        } catch (IOException ex) {
            logger.error("Error COULD NOT START SERVER");
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5);
        server.startServer();
    }

}

package com.mcimp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientHandler implements Runnable {
    private static final Logger logger = LogManager.getLogger(ClientHandler.class);

    private final int timeout;

    private Socket socket;

    public ClientHandler(Socket socket, int timeout) {
        this.socket = socket;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            socket.setSoTimeout(timeout);



            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        } catch (IOException e) {
            logger.error(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.mcimp.server;

import java.net.Socket;

public class ClientHandler implements Runnable {
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
        } finally {

        }
    }
}

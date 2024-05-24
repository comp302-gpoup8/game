package com.example.domain.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * GameClient class
 * While I was writing I noticed it was very similar to clienthandler so I made it a subclass.
 * At the very least we should change the name of the superclass I guess.
 */
public class GameClient extends ClientHandler {

    private String serverAdress;
    private int serverPort;

    public GameClient(String address, int port) throws UnknownHostException, IOException {
        super(new Socket(address, port));
        serverAdress = address;
        serverPort = port;
    }

    public GameClient(Socket socket) {
        super(socket);
        serverAdress = socket.getLocalAddress().toString();
        serverPort = socket.getLocalPort();
    }
}

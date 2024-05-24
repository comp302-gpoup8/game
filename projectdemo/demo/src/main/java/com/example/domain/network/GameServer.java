package com.example.domain.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Game Server class for network implementation
 */
public class GameServer {
    
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    public GameServer (int port){
        try {
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<>();

            while (true){
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket);
                clients.add(handler);
                new Thread(handler).start();
                //TODO: Makesure all clients broadcast.
            }
        } catch (IOException e){
            System.out.println("Problem with GameServer");
            e.printStackTrace();
        }
    }
}

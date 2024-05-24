package com.example.domain.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Handles server clients for the game server.
 * Helper class, but I wanted to give it own file to make managing it easier.
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    /**
     * Constructor opens the input & output streams. Should not cause errors really.
     * @param socket
     */
    public ClientHandler(Socket socket){
        this.socket = socket;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e){
            System.out.println("Problem with client handler");
            e.printStackTrace();
        }
    }

    /**
     * Runs the thread for the server client.
     * TODO: The input should much our controller input– either we implement that here,
     * or change how we handled it althogether. Maybe that'll be easier actually.
     */
    @Override
    public void run(){
        try {
            while (true){
                Object data = input.readObject();
                broadcast(data);
            }
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Problem with run method in clienthandler.");
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Problem with client handler while attempting to close the socket.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Broadcasts the output of the client.
     * @param data
     */
    public void broadcast(Object data){
        try {
            output.writeObject(data);
            output.flush();
        } catch (IOException e){
            System.out.println("Clienthandler broadcast method caused an exception.");
            e.printStackTrace();
        }
    }
}

package com.example.domain.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

public class GameLobby {
    private ServerSocket serverSocket;
    private GameClient host;
    private GameClient guest;
    private boolean guestReady;

    public GameLobby(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Lobby created, waiting for players to join...");

            host = new GameClient(serverSocket.accept());
            System.out.println("Host player connected.");

            guest = new GameClient(serverSocket.accept());
            System.out.println("Guest player connected.");

            guestReady = false;

            new Thread(new HostHandler()).start();
            new Thread(new GuestHandler()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 
     * The classes below don't need to be here for long term
     * We just have to figure out how to do this switch in client handler without making it too long.
     */
    private class HostHandler implements Runnable {
        @Override
        public void run() {
            try {
                ObjectOutputStream output = new ObjectOutputStream(host.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(host.getInputStream());

                while (true) {
                    String command = (String) input.readObject();
                    if (command.equals("startGame")) {
                        if (guest == null || !guestReady) {
                            output.writeObject("Cannot start game. Either guest is not connected or not ready.");
                        } else {
                            output.writeObject("Game starting for host...");
                            // TODO: START GAME
                        }
                        output.flush();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private class GuestHandler implements Runnable {
        @Override
        public void run() {
            try {
                ObjectOutputStream output = new ObjectOutputStream(guest.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(guest.getInputStream());

                while (true) {
                    String command = (String) input.readObject();
                    if (command.equals("ready")) {
                        guestReady = true;
                        output.writeObject("Guest is ready.");
                    } else if (command.equals("notReady")) {
                        guestReady = false;
                        output.writeObject("Guest is not ready.");
                    }
                    output.flush();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

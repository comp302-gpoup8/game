package com.example.visual;


import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LobbyGuestGUI extends JFrame {
    private JTextField hostField;
    private JTextField guestField;
    private JButton readyButton;
    private boolean isReady;
    private Socket socket;
    private ObjectOutputStream output;

    public LobbyGuestGUI(String hostName, String guestName, Socket socket) {
        this.socket = socket;
        this.isReady = false;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Lobby Guest");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 1));

        hostField = new JTextField(hostName);
        hostField.setEditable(false);
        guestField = new JTextField(guestName);
        guestField.setEditable(false);

        playerPanel.add(hostField);
        playerPanel.add(guestField);

        readyButton = new JButton("Ready");
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isReady = !isReady;
                try {
                    output.writeObject(isReady ? "ready" : "notReady");
                    output.flush();
                    readyButton.setText(isReady ? "Not Ready" : "Ready");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        add(playerPanel, BorderLayout.CENTER);
        add(readyButton, BorderLayout.EAST);

        setVisible(true);
    }
}

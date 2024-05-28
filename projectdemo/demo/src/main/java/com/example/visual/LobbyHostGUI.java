package com.example.visual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LobbyHostGUI extends JPanel {
    private JTextField hostField;
    private JTextField guestField;
    private JButton startButton;
    private JLabel statusLabel;
    @SuppressWarnings("unused")
    private Socket socket;
    private ObjectOutputStream output;

    public LobbyHostGUI(String hostName, Socket socket) {
        this.socket = socket;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        setSize(400, 200);
        setLayout(new BorderLayout());

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 1));

        hostField = new JTextField(hostName);
        hostField.setEditable(false);
        guestField = new JTextField("Waiting for guest...");
        guestField.setEditable(false);

        playerPanel.add(hostField);
        playerPanel.add(guestField);

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    output.writeObject("startGame");
                    output.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        statusLabel = new JLabel("Guest is not ready.");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(playerPanel, BorderLayout.CENTER);
        add(startButton, BorderLayout.EAST);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void updateGuestStatus(String guestName, boolean ready) {
        guestField.setText(guestName);
        statusLabel.setText(ready ? "Guest is ready." : "Guest is not ready.");
    }
}

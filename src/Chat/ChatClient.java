package Chat;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.LocalDateTime;

public class ChatClient extends Thread {
    DatagramSocket socket;
    int minPort = 55555;
    DatagramPacket packet;
    JTextArea textArea;

    public ChatClient(JTextArea textArea) {
        this.textArea = textArea;
        socket = null;
        byte[] data = new byte[1024];
        try {
            socket = new DatagramSocket(minPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[256];
            packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //String sender = packet.getAddress().getHostAddress();
            //String sender = packet.getAddress().getHostName();
            //String sender = packet.getAddress().getCanonicalHostName();
            String message = new String(packet.getData(),
                    0, packet.getLength());
            textArea.append(packet.getAddress() + ": " + message +"\n");

            System.out.println("Meddelande från " +
                    packet.getAddress().getHostAddress());
            System.out.println("Meddelande från " +
                    packet.getAddress().getHostName());

            System.out.println(message);
        }
    }

}

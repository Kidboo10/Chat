package Chat;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ChatServer extends JFrame{
    JFrame frame = new JFrame();
    JPanel chatPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton connect = new JButton("Connect");
    JButton disconnect = new JButton("Disconnect");
    JButton close = new JButton ("Exit");
    JTextField chatField = new JTextField();
    JTextArea chatArea = new JTextArea();
    MulticastSocket so;
    ChatClient chatClient;
    DatagramSocket socket = new DatagramSocket();
    InetAddress local = InetAddress.getLocalHost();
    InetAddress toAdr2;
    int toPort = 55555;

    public ChatServer() throws IOException {
        ChatClient chatClient = new ChatClient(chatArea);
        chatClient.start();
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 400);
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(chatPanel, BorderLayout.CENTER);
        chatPanel.setLayout(new BorderLayout());
        buttonPanel.add(connect);
        buttonPanel.add(disconnect);
        buttonPanel.add(close);
        close.addActionListener(e -> System.exit(0));


        chatPanel.add(chatArea, BorderLayout.CENTER);
        chatArea.setEditable(false);
        chatPanel.add(chatField,BorderLayout.SOUTH);
        frame.setVisible(true);

        disconnect.addActionListener(e -> {
            socket.disconnect();
            socket.close();
            chatArea.append("Disconnected" + "\n");
        });

        connect.addActionListener(e -> {
            chatArea.append("Connected" + "\n");
            try {
                socket = new DatagramSocket();
                toAdr2 = InetAddress.getByName("172.20.201.200");
                local = InetAddress.getLocalHost();
            }catch (UnknownHostException | SocketException ex){
                ex.printStackTrace();
            }
        });
        chatField.addActionListener(e -> {
            String message = chatField.getText();

            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, local, toPort);
            DatagramPacket packet2 = new DatagramPacket(data, data.length, toAdr2, toPort);
            try {
                socket.send(packet);
                socket.send(packet2);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            chatField.setText("");
        });
    }

    public static void main(String[] args) throws IOException {
        ChatServer cc = new ChatServer();
    }
}
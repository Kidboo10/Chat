import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

public class Sender {


    public static void main(String[] args) throws UnknownHostException, SocketException, IOException, InterruptedException {
/*
        InetAddress localHost = InetAddress.getLocalHost();
        String hostName = localHost.getHostName();
        String hostAddress = localHost.getHostAddress();
        boolean multicast = localHost.isMulticastAddress();
        InetAddress loopBack = localHost.getLoopbackAddress();
        System.out.println("get Localhost: " + localHost);
        System.out.println("get Hostname: " + hostName);
        System.out.println("get Hostaddress: " + hostAddress);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));


 */

        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));

        //InetAddress toAdr = InetAddress.getLocalHost();
        InetAddress toAdr = InetAddress.getByName("172.20.201.200");
        int toPort = 55555;
        DatagramSocket socket = new DatagramSocket();
        String message ="";
        String message2 = "this is who";
      //  while (true) {
            while ((message = in.readLine()) != null) {
                if (message.equals("bye")) System.exit(0);
            byte[] data = message.getBytes();
            //byte[] data1 = message2.getBytes();
            DatagramPacket packet = new DatagramPacket(data,data.length, toAdr, toPort);
           // DatagramPacket packet1 = new DatagramPacket(data1,data1.length, toAdr, toPort);
            socket.send(packet);
            //Thread.sleep(3000);
           // socket.send(packet1);
           // Thread.sleep(4000);

        }
    }
}
    /*
    BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));

    InetAddress toAdr = null;
        try {
        toAdr = InetAddress.getLocalHost();
        int toPort = 55555;
        DatagramSocket socket = new DatagramSocket();
        String message;
        System.out.println("Vad har du på hjärtat? ");
        while ((message = in.readLine()) != null) {
            if (message.equals("bye")) System.exit(0);
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data,
                    data.length, toAdr, toPort);
            socket.send(packet);
            System.out.println("Vad har du på hjärtat?  ");
        }
        System.exit(0);
    } catch (IOException e) {
        e.printStackTrace();
    }

     */


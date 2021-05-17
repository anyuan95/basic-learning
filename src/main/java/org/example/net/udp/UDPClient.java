package org.example.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/**
 * @author anyuan
 * @since 2021-03-27 20:53
 */
public class UDPClient {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        String message = "hello world.";
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        InetAddress localHostAddress = InetAddress.getLocalHost();
        System.out.println(localHostAddress);
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, localHostAddress, 6666);
        datagramSocket.send(datagramPacket);
    }
}

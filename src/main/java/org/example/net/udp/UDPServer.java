package org.example.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/**
 * @author anyuan
 * @since 2021-03-27 20:53
 */
public class UDPServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(6666);
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);

        while (true) {
            datagramSocket.receive(datagramPacket);
            String content = new String(datagramPacket.getData(), StandardCharsets.UTF_8);
            System.out.println(content);
        }
    }
}

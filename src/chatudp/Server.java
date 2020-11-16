/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatudp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/**
 *
 * @author ASUS
 */
public class Server {

    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        boolean bye = false;
        
        while (true) {
            
            byte[] receivebuffer = new byte[1024]; //untuk menerima data berupa byte array
            byte[] sendbuffer = new byte[1024]; //untuk mengirim data berupa byte array
            // konstruktor untuk menerima datagram
            DatagramPacket recvdpkt = new DatagramPacket(receivebuffer, receivebuffer.length);
            serverSocket.receive(recvdpkt);
            InetAddress IP = recvdpkt.getAddress(); //untuk menerima ip farid
            int portno = recvdpkt.getPort(); //untuk menerima port farid
            String clientdata = new String(recvdpkt.getData()); // untuk menerima data/inputan yang dikirim farid
            System.out.println("\nClient : " + clientdata);
            System.out.print("\nServer : ");
            BufferedReader serverRead = new BufferedReader(new InputStreamReader(System.in));
            String serverdata = serverRead.readLine();

            sendbuffer = serverdata.getBytes(); //sendbuffer berisi inputan byte array dalam clientdata
            // DatagramPacket untuk mengirim datagram yang berkonstruktor isi inputan (sendbuffer) ,
            DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP, portno);
            serverSocket.send(sendPacket);
            //untuk mengecek kondisi server data jika "bye" maka koneksi berakhir
            if (serverdata.equalsIgnoreCase("bye")) {
                System.out.println("connection ended by server");
                break;
            }
        }
        serverSocket.close();
    }
}
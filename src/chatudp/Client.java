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
public class Client {
    public static void main(String[] args) throws SocketException, IOException {
        
      BufferedReader clientRead =new BufferedReader(new InputStreamReader(System.in));
      InetAddress IP = InetAddress.getByName("10.10.100.19");
      DatagramSocket clientSocket = new DatagramSocket();
      
      while(true) {
      byte[] sendbuffer = new byte[1024]; //untuk mengirim data berupa byte array
      byte[] receivebuffer = new byte[1024]; //untuk menerima data berupa byte array
      
      System.out.print("\nClient: "); 
      String clientData = clientRead.readLine(); // untuk readLine inputan
      sendbuffer = clientData.getBytes(); 
      DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP, 2255);
      // Client socket akan memanggil method send yang isinya sendPacket
      clientSocket.send(sendPacket);
      
      //untuk mengecek kondisi server data jika "bye" maka koneksi berakhir
      if(clientData.equalsIgnoreCase("bye")) {
          System.out.println("Connection ended by client");
          break;
      }
      //DatagramPacket untuk menerima datagram yang berkonstruktor receivebuffer dan panjangnya
      DatagramPacket receivePacket = new DatagramPacket(receivebuffer, receivebuffer.length);
      clientSocket.receive(receivePacket);
      String serverData = new String(receivePacket.getData()); // untuk menerima data/inputan yang dikirim kevin
      System.out.println("\nServer: " + serverData); //pesan kevin ditampilkan disini
          
      }
      clientSocket.close();
    }
}

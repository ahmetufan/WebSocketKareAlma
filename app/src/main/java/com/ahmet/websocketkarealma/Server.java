package com.ahmet.websocketkarealma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        String clientGelen;
        ServerSocket serverSocket = null;
        Socket socket = null;

        int sayi;

        try {

            //  Server 7755 portundan Client'ı dinliyor
            serverSocket = new ServerSocket(7755);

        } catch (Exception e) {
            System.out.println("Port Hatası !!!");
        }
        System.out.println("SERVER BAĞLANTI İÇİN HAZIR");


        //   Bağlantı sağlamadan program bir alt satırdaki kod parçasına geçmez (accept)
        socket = serverSocket.accept();

        //   Client'a veri gönderimi için kullandığımız PrintWriter nesnesi oluşturulur
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        //   Client'dan gelen verileri tutan BufferedReader nesnesi oluşturulur
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        while ((clientGelen = in.readLine()) != null) {
            System.out.println("Client'den gelen veri = " + clientGelen);
            sayi = Integer.valueOf(clientGelen);
            out.println(sayi * sayi);

            //  out.println(sayi * sayi) komutu ile istemciden gelen sayının karesini alıp tekrar istemciye iletiyoruz.
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();


    }


}

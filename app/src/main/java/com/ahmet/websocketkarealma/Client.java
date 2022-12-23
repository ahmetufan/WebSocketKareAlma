package com.ahmet.websocketkarealma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws IOException {

        islem();
    }

    public static void islem() throws UnknownHostException, IOException {

        Socket socket = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String deger;

        try {

            //   server 'a localhost ve 7755 portu üzerinden başlantı sağlanıyor
            socket = new Socket("localhost", 7755);

        } catch (Exception e) {
            System.out.println("Port Hatası");
        }

        //* Server'a veri gönderimi için kullandığımız PrintWriter nesnesi oluşturduk
        printWriter = new PrintWriter(socket.getOutputStream(), true);

        //* Server'dan gelen verileri tutan BufferedReader nesnesi oluşturulur
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("Server'a gönderilecek sayı giriniz : ");

        //* Gönderilecek sayının girişi yapılıyor
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));

        while ((deger = data.readLine()) != null) {
            //  out.println(deger); koduyla Server ‘a datamızı gönderiyoruz.
            printWriter.println(deger);

            //  clientGelen=bufferedReader.readLine(); komutu istemciden gelen datayı okur ve clientGelen değişkenine aktarır.
            System.out.println("Server'dan gelen sonuç =  " + bufferedReader.readLine());
            System.out.println("Server'a gönderilecek sayi giriniz: ");
        }
        printWriter.close();
        bufferedReader.close();
        data.close();
        socket.close();
    }

}

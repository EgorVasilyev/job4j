package io.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public void runClient() throws IOException {
        int port = 5000;
        String ip = "192.168.0.164";
        Socket socket = new Socket(InetAddress.getByName(ip), port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        do {
            String toServer = console.nextLine();
            out.println(toServer);
            String str;
            while (!(str = in.readLine()).isEmpty()) {
                System.out.println(str);
            }
        } while (true);
    }

    public static void main(String[] args) throws IOException{
        new Client().runClient();
    }
}

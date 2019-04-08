package io.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;

    public Client(int port, String ip) throws IOException {
        this.socket = new Socket(InetAddress.getByName(ip), port);
    }

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void runClient() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        do {
            out.println(new Scanner(System.in).nextLine());
            String str;
            while (!(str = in.readLine()).isEmpty()) {
                System.out.println(str);
            }
        } while (true);
    }
}

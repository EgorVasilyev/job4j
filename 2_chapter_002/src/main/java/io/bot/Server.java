package io.bot;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private final Socket socket;
    public Server(Socket socket) {
        this.socket = socket;
    }
    public void runServer() throws IOException {
      //  Socket socket = new ServerSocket(5000).accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String ask;
        HashMap<String, String> answers = new HashMap<String, String>();
        BufferedReader answerBox =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        "C:\\Users\\georg\\Desktop\\Бот\\мудрый Оракл.txt"), "Cp1251"
                        )
                );
        String line;
        while((line = answerBox.readLine()) != null) {
            String[] questionAndAnswer = line.split("___");
            answers.putIfAbsent(questionAndAnswer[0], questionAndAnswer[1]);
        }
        System.out.println("Client is here");
        do {
            ask = in.readLine();
            System.out.println("client said: " + ask);
            if (answers.containsKey(ask.toLowerCase())) {
                out.println(answers.get(ask));
                out.println();
            } else if (!"выход".equals(ask.toLowerCase())) {
                out.println(answers.get("Не понимаю"));
                out.println();
            }
        } while (!"выход".equals(ask.toLowerCase()));
    }

    public static void main(String[] args) throws IOException {
        new Server(new ServerSocket(5000).accept()).runServer();
    }
}

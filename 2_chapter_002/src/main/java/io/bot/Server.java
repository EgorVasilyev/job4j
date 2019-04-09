package io.bot;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private Socket socket;
    private final HashMap<String, String> answers;

    private Server(String pathTextBot) throws IOException {
        this.answers = new HashMap<String, String>();
        BufferedReader answerBox =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(pathTextBot)
                        )
                );
        String line;
        while((line = answerBox.readLine()) != null) {
            String[] questionAndAnswer = line.split("___");
            answers.putIfAbsent(questionAndAnswer[0], questionAndAnswer[1]);
        }
    }

    public Server(Socket socket, String pathTextBot) throws IOException {
        this(pathTextBot);
        this.socket = socket;
    }

    public Server(int port, String pathTextBot) throws IOException {
        this(pathTextBot);
        this.socket = new ServerSocket(port).accept();
    }

    public void runServer() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String ask;
        System.out.println("Client is here");
        do {
            ask = in.readLine();
            System.out.println("client said: " + ask);
            if (answers.containsKey(ask.toLowerCase())) {
                System.out.println("server answered: " + answers.get(ask));
                out.println(answers.get(ask));
                out.println();
            } else if (!"выход".equals(ask.toLowerCase())) {
                System.out.println("server answered: Не понимаю");
                out.println("Не понимаю");
                out.println();
            }
        } while (!"выход".equals(ask.toLowerCase()));
    }

/*    public static void main(String[] args) throws IOException {
        new Server(5000, "C:\\Users\\georg\\AppData\\Local\\Temp\\botOracle.txt").runServer();
    }*/
}

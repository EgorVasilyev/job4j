package io;

import java.io.*;
import java.util.Random;

/**
 * ConsoleChat - класс консольный чат.
 */
public class ConsoleChat {

    private BufferedReader bufferReader;
    private BufferedWriter bufferWriter;
    private String emulateConsoleInput;

    public ConsoleChat(String loggerPath, String phrasesPath) {
        try {
            File logger = new File(loggerPath + "\\logger.txt");
            this.bufferReader =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(phrasesPath), "Cp1251"
                            )
                    );
            FileWriter writer = new FileWriter(logger.getPath(), true);
            this.bufferWriter = new BufferedWriter(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Конструктор для тестирования
     * @param loggerPath - директория, куда нужно сохранить log файл
     * @param phrasesPath - путь к файлу с фразами
     * @param emulateConsoleInput - имитация ввода текста
     */
    public ConsoleChat(String loggerPath, String phrasesPath, String emulateConsoleInput) {
        this(loggerPath, phrasesPath);
        this.emulateConsoleInput = emulateConsoleInput;
    }

    /**
     * Метод - запуск чата
     * @throws IOException
     */
    public void letsChat() throws IOException {
        boolean stopAnswer = false;
        boolean stopChat = false;
        Object[] phrasesArray = bufferReader.lines().toArray();
        int indexForEmulatedInput = 0;
        try {
            while (!stopChat) {
                String textFromConsole;
                if (emulateConsoleInput == null) {
                    textFromConsole = new BufferedReader(new InputStreamReader(System.in)).readLine();
                } else {
                    textFromConsole = emulateConsoleInput.split("\n")[indexForEmulatedInput++];
                    System.out.println("user:          " + textFromConsole);
                }
                String texttoLowerCase = textFromConsole.toLowerCase();
                switch (texttoLowerCase) {
                    case "закончить":
                        stopChat = true;
                        stopAnswer = true;
                        break;
                    case "стоп":
                        stopAnswer = true;
                        break;
                    case "продолжить":
                        stopAnswer = false;
                        break;
                    default:
                }
                bufferWriter.write("user:          " + textFromConsole);
                bufferWriter.newLine();

                if (!stopAnswer) {
                    int number = new Random().nextInt(phrasesArray.length);
                    String phrase = (String) phrasesArray[number];
                    System.out.println("random phrase: " + phrase);
                    bufferWriter.write("random phrase: " + phrase);
                    bufferWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            bufferWriter.close();
        }
    }
}

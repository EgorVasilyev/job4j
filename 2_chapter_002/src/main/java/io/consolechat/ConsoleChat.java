package io.consolechat;

import java.io.*;
import java.util.HashMap;

/**
 * ConsoleChat - класс консольный чат.
 */
public class ConsoleChat {

    private BufferedWriter bufferWriter;
    private String emulateConsoleInput;
    private Answer answer;
    private HashMap<String, Action> actions;
    private boolean stopAnswer = false;
    private boolean stopChat = false;

    public ConsoleChat(String loggerPath, String phrasesPath) {
        try {
            this.answer = new Answer(new File(phrasesPath));
            FileWriter writer = new FileWriter(
                    new File(loggerPath + File.separator + "logger.txt").getPath(), true);
            this.bufferWriter = new BufferedWriter(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.actions = new HashMap<>(3);
        this.createActions();
    }

    private void createActions() {
        class AllStop implements Action {
            @Override
            public void execute() {
                stopChat = true;
                stopAnswer = true;
            }
        }
        class StopAnswer implements Action {
            @Override
            public void execute() {
                stopAnswer = true;
            }
        }
        class ContinueAnswer implements Action {
            @Override
            public void execute() {
                stopAnswer = false;
            }
        }
        this.actions.put("закончить", new AllStop());
        this.actions.put("стоп", new StopAnswer());
        this.actions.put("продолжить", new ContinueAnswer());
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
                String textToLowerCase = textFromConsole.toLowerCase();

                if (this.actions.containsKey(textToLowerCase)) {
                    this.actions.get(textToLowerCase).execute();
                }

                bufferWriter.write("user:          " + textFromConsole);
                bufferWriter.newLine();

                if (!stopAnswer) {
                    String randomLine = this.answer.randomLine();
                    System.out.println("random phrase: " + randomLine);
                    bufferWriter.write("random phrase: " + randomLine);
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

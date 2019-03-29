package io;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ConsoleChatTest {
    @Test
    public void test() {
        String path = System.getProperty("java.io.tmpdir");
        System.out.println(path);
        File folderChat = new File(path + "\\folderChat");
        assertTrue(folderChat.mkdir());

        File phrases = new File(path + "\\folderChat\\phrases.txt");
        BufferedWriter bufferWriter = null;
        try {
            assertTrue(phrases.createNewFile());
            FileWriter writer = new FileWriter(phrases);
            bufferWriter = new BufferedWriter(writer);
            String phrasesString = "1 phrase\n2 phrase\n3 phrase\n4 phrase"
                    + "\n5 phrase\n6 phrase\n7 phrase";
            int index = 0;
            while (index != 7) {
                String phrase = phrasesString.split("\n")[index++];
                bufferWriter.write(phrase);
                bufferWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ConsoleChat chat = new ConsoleChat(
                path + "\\folderChat",
                path + "\\folderChat\\phrases.txt",
                "первая имитация ввода\nвторая имитация ввода"
                        + "\nстоп\nтретья имитация ввода\nчетвертая имитация ввода\nпродолжить\nзакончить"
        );

        //или запустить этот код с указанием путей сохранения лога и хранения файла с фразами и вводом с клавиатуры
        /*ConsoleChat chat = new ConsoleChat(
                "путь для хранения log-файла",
                "путь файла с фразами");*/

        try {
            chat.letsChat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package io.consolechat;

import java.io.*;
import java.util.Random;

/**
 * Answer - класс ответ.
 */
public class Answer {
    private Object[] phrasesArray;
    public Answer(final File file) {
        try (BufferedReader bufferReader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(file), "Cp1251"
                             )
                     )
        ) {
            this.phrasesArray = bufferReader.lines().toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод, возвращающий случайную строку из текстового файла, переданного в конструктор.
     * @return случайная строка
     */
    public String randomLine() {
        int number = new Random().nextInt(this.phrasesArray.length);
        return (String) this.phrasesArray[number];
    }
}

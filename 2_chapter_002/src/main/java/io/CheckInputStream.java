package io;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class CheckInputStream {
    public static boolean isEvenNumber(InputStream in) throws IOException {
        boolean result = true;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int value;
            int finishValue = 0;
            while ((value = in.read()) != -1) {
                out.reset();
                finishValue = value;
                out.write(finishValue);
                if (!Character.isDigit(out.toString().charAt(0))) {
                    result = false;
                    break;
                }
            }
            if (result && !(Integer.parseInt(out.toString()) % 2 == 0)) {
                result = false;
            }
        } finally {
            in.close();
            out.close();
        }
        return result;
    }
    public static void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try {
            try (final PrintStream writer = new PrintStream(out);
                 final BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                reader.lines()
                        //разделяет стрим на стримы по пробелу
                        .map(stringStream -> Arrays.stream(stringStream.split(" ")))
                        //объединяет стримы в стрим уже без пробелов
                        .reduce(Stream::concat).get()
                        //заменяет запрещенное слово на ""
                        .map(stringStream ->
                                Arrays.stream(abuse).reduce(stringStream, (separateWord, badWord) ->
                                       separateWord.replaceAll(badWord, "")))
                        //если стрим не пустой, то пропускает его дальше
                        .filter(stringStream ->
                                !stringStream.isEmpty())
                        //разделяет оставшиеся стримы пробелами
                        .map(stringStream ->
                                stringStream + " ")
                        //выводит стрим в write
                        .forEach(writer::print);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

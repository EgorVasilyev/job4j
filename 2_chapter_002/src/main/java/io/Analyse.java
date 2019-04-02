package io;

import java.io.*;

public class Analyse {
    public static void unavailable(String source, String target) throws IOException {
        new File(target).createNewFile();
        boolean serverIsNotWork = false;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
             BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(target))) {
            Object[] buffer = bufferedReader.lines().toArray();
            for (Object line : buffer) {
                String stringLine = (String) line;
                if (!serverIsNotWork
                        && (stringLine.startsWith("400 ")
                        || stringLine.startsWith("500 "))) {
                    serverIsNotWork = true;
                    bufferWriter.write(stringLine.substring(4));
                }
                if (serverIsNotWork && (stringLine.startsWith("200 ") || stringLine.startsWith("300 "))) {
                    serverIsNotWork = false;
                    bufferWriter.write(";" + stringLine.substring(4));
                    bufferWriter.newLine();
                }
            }
        }
    }
}

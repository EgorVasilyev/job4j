package io;

import java.util.ArrayList;
/**
 * Class MainToZip - Запуск jar архиватора.
 */
public class MainToZip {
    public static void main(String[] args) {
        String directory = "";
        ArrayList<String> exts = new ArrayList<>();
        String output = "";
        String prev = "";
        for (String s : args) {
            if (prev.equals("-d")) {
                directory = s;
            } else if (prev.equals("-e")) {
                exts.add(s);
            } else if (prev.equals("-o")) {
                output = s;
            }
            prev = s;
        }
        Args arguments = new Args(directory, exts, output);
        ToZip toZip = new ToZip();
        toZip.createZip(arguments);
        //java -jar toZip.jar -d C:\projects\job4j\2_chapter_002 -e .java -o C:\Temp
    }
}
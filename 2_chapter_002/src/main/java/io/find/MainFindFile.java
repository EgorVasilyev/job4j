package io.find;

import java.io.File;
import java.io.IOException;

/**
 * Class MainFindFile - Запуск jar поиска файла.
 */
public class MainFindFile {
    public static void main(String[] args) throws IOException {
        String directory = "";
        String output = "";
        String parametr = "";
        boolean regex = false;
        String prev = "";
        boolean badInput = false;
        for (String s : args) {
            if (prev.equals("-d")) {
                if (!new File(s).exists()) {
                    System.out.println("-d, directory is incorrect!");
                    badInput = true;
                }
                directory = s;
            } else if (prev.equals("-o")) {
                if (!new File(s).exists()) {
                    System.out.println("-o, output is incorrect!");
                    badInput = true;
                }
                output = s;
            } else if (prev.equals("-n")) {
                parametr = s;
            } else if (prev.equals("-r")) {
                if (!s.equals("false") && !s.equals("true")) {
                    System.out.println("-r, regex is incorrect!");
                    badInput = true;
                }
                regex = Boolean.valueOf(s);
            }
            prev = s;
        }
        if (badInput) {
            showInstruction();
        }
        Args arguments = new Args(directory, output, parametr, regex);
        FindFile findFile = new FindFile(arguments);
        findFile.files();
        //java -jar 2_chapter_002.jar -d C:\projects\job4j\2_chapter_002\asd -o C:\Users\georg\AppData\Local\Temp\ -n *.java -r false
    }
    private static void showInstruction() {
        StringBuilder directory = new StringBuilder(System.lineSeparator())
                .append("Instructions:")
                .append(System.lineSeparator())
                .append("-d, directory       This option must be a path that contains the required files")
                .append(System.lineSeparator())
                .append("                    ")
                .append("For example: -d C:\\Program Files");
        StringBuilder output = new StringBuilder(
                "-o, output          This option must be a target path for creating result file foundFiles.txt")
                .append(System.lineSeparator())
                .append("                    ")
                .append("For example: -o C:\\Users\\Public");
        StringBuilder parameter = new StringBuilder(
                "-n, parameter       This option must be a full name, a mask or a regular expression for search")
                .append(System.lineSeparator())
                .append("                    ")
                .append("For example: -n *.exe");
        StringBuilder regex = new StringBuilder(
                "-r, regex           This option must be true, if parameter for search is regular expression,")
                .append(System.lineSeparator())
                .append("                    ")
                .append("or false, if parameter for search is mask or full name (not regular expression)")
                .append(System.lineSeparator())
                .append("                    ")
                .append("For example: -r false");
        StringBuilder example = new StringBuilder("Example for input:")
                .append(System.lineSeparator())
                .append("java -jar 2_chapter_002.jar -d C:\\Program Files -o C:\\Users\\Public -n *.exe -r false");
        StringBuilder instruction = new StringBuilder(directory)
                .append(System.lineSeparator())
                .append(output)
                .append(System.lineSeparator())
                .append(parameter)
                .append(System.lineSeparator())
                .append(regex)
                .append(System.lineSeparator())
                .append(example)
                .append(System.lineSeparator());
        System.out.println(instruction);
    }
}
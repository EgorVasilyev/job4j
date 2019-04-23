package io.find;

import java.io.IOException;

/**
 * Class MainFindFile - Запуск jar поиска файла.
 */
public class MainFindFile {
    public static void main(String[] args) throws IOException {
        String directory = "";
        String output = "";
        String parametr = "";
        String regex = "";
        String prev = "";
        for (String s : args) {
            if (prev.equals("-d")) {
                directory = s;
            } else if (prev.equals("-o")) {
                output = s;
            } else if (prev.equals("-n")) {
                parametr = s;
            } else if (prev.equals("-r")) {
                regex = s;
            }
            prev = s;
        }
        Args arguments = new Args(directory, output, parametr, regex);
        FindFile findFile = new FindFile(arguments);
        findFile.files();
        //java -jar 2_chapter_002.jar -d C:\projects\job4j\2_chapter_002\asd -o C:\Users\georg\AppData\Local\Temp\ -n *.java -r false
    }
}
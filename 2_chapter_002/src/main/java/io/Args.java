package io;

import java.io.File;
import java.util.ArrayList;

public class Args {
    private String directory;
    private ArrayList<String> exts;
    private String output;

    public Args(String directory, ArrayList<String> exts, String output) {
        this.directory = directory;
        this.exts = exts;
        this.output = output;
    }
    /**
     * Method directory. Получение директории которую нужно архивировать.
     *
     * @return Файл.
     */
    public File directory() {
        return new File(this.directory);
    }
    /**
     * Method exts. Получение коллекции разрешаемых расширений.
     *
     * @return Список.
     */
    public ArrayList<String> exts() {
        return this.exts;
    }
    /**
     * Method output. Получение директории для размещения архива.
     *
     * @return Файл.
     */
    public File output() {
        return new File(this.output);
    }
    @Override
    public String toString() {
        return "Args{" + "directory='" + directory + '\'' + ", exts=" + exts + ", output='" + output + '\'' + '}';
    }
}

package io;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Search {
    /**
     * Method files. Поиск файлов с нужными расширениями по каталогу
     * @param parent Проверяемый каталог
     * @param exts Искомые расширения.
     */
    public static List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<File>();
        File root = new File(parent);
        queue.offer(root);
        while (!queue.isEmpty()) {
            File checkableFile = queue.poll();
            if (checkableFile.isDirectory()) {
                System.out.println("search in the directory:    " + checkableFile.getAbsolutePath());
                for (File innerFile : checkableFile.listFiles()) {
                    queue.offer(innerFile);
                }
            } else {
                if (allowableExt(checkableFile, exts)) {
                    System.out.println("found file:                 " + checkableFile.getAbsolutePath());
                    result.add(checkableFile);
                }
            }
        }
        return result;
    }
    /**
     * Method allowableExt. Проверка расширения файла на валидность
     * @param checkableFile Проверяемый файл
     * @param exts Разрешаемые расширения.
     */
    private static boolean allowableExt(File checkableFile, List<String> exts) {
        return exts.contains(
                checkableFile.getName().substring(checkableFile.getName().indexOf("."))
        );
    }
}

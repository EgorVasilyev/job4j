package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class ToZip - Архиватор.
 *
 * @author Egor Vasilyev
 * @since 27.03.2019
 * @version 1
 */
public class ToZip {
    private ZipOutputStream zout;
    /**
     * Method createZip. Метод архивации.
     * @param arguments Аргументы.
     */
    public void createZip(Args arguments) {
        File in = arguments.directory();
        File out = arguments.output();
        ArrayList<String> exts = arguments.exts();
        try {
            this.zout = new ZipOutputStream(new FileOutputStream(new File(out, in.getName() + ".zip")));
            makeZipTree(in, exts);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.zout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Method makeZipTree. Создание каталога архивируемых файлов.
     * @param in Директория, которую нужно архивировать.
     * @param exts Разрешаемые расширения.
     */
    private void makeZipTree(File in, ArrayList<String> exts) throws IOException {
        Queue<File> queue = new LinkedList<File>();
        queue.offer(in);
        while (!queue.isEmpty()) {
            File checkableFile = queue.poll();
            if (checkableFile.isDirectory() && checkableFile.list().length != 0) {
                //System.out.println("directory:    " + checkableFile.getAbsolutePath());
                String path =
                        checkableFile.getAbsolutePath().replace(in.getAbsolutePath() + "\\", "") + "\\";
                ZipEntry entry1 = new ZipEntry(path);
                this.zout.putNextEntry(entry1);
                this.zout.closeEntry();
                for (File innerFile : checkableFile.listFiles()) {
                    queue.offer(innerFile);
                }
            } else {
                if (allowableExt(checkableFile, exts)) {
                    //System.out.println("found file:   " + checkableFile.getAbsolutePath());
                    FileInputStream fis = new FileInputStream(checkableFile);
                    ZipEntry entry1 = new ZipEntry(checkableFile.getPath().replace((in.getPath() + "\\"), ""));
                    this.zout.putNextEntry(entry1);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer, 0, fis.available());
                    this.zout.write(buffer, 0, fis.available());
                    this.zout.closeEntry();
                    fis.close();
                }
            }
        }
    }
    /**
     * Method allowableExt. Проверка расширения файла на валидность
     * @param checkableFile Проверяемый файл
     * @param exts Разрешаемые расширения.
     */
    private boolean allowableExt(File checkableFile, ArrayList<String> exts) {
        return exts.contains(
                checkableFile.getName().substring(checkableFile.getName().indexOf("."))
        );
    }
}

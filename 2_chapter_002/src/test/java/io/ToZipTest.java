package io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ToZipTest {
    @Test
    public void whenZipFileIsCreatedThenRecreatIsFalse() {
        String fileSeparator = System.getProperty("file.separator");
        String path = System.getProperty("java.io.tmpdir");
        System.out.println(path);

        File folder1 = new File(path + "\\folder11");
        File folder2 = new File(path + "\\folder11\\folder2");
        File folder3 = new File(path + "\\folder11\\folder3");
        File folder4 = new File(path + "\\folder11\\folder2\\folder4");

        assertTrue(folder1.mkdir());
        assertTrue(folder2.mkdir());
        assertTrue(folder3.mkdir());
        assertTrue(folder4.mkdir());

        File file1 = new File(path + "\\folder11\\file1.txt");
        File file2 = new File(path + "\\folder11\\file2.txt");
        File file3 = new File(path + "\\folder11\\folder2\\file3.txt");
        File file4 = new File(path + "\\folder11\\folder3\\file4.ini");
        File file5 = new File(path + "\\folder11\\folder2\\folder4\\file5.ini");

        try {
            assertTrue(file1.createNewFile());
            assertTrue(file2.createNewFile());
            assertTrue(file3.createNewFile());
            assertTrue(file4.createNewFile());
            assertTrue(file5.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ToZip toZip = new ToZip();
        ArrayList<String> exts = new ArrayList<String>(Arrays.asList(".txt", ".ini"));
        toZip.createZip(new Args(folder1.getPath(), exts, path));

        File checkZip = new File(path + fileSeparator + "folder11.zip");
        try {
            assertFalse(checkZip.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

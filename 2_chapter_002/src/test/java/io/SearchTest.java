package io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SearchTest {
    @Test
    public void whenFoldersContainsTXTFilesThen5files() {
        String path = System.getProperty("java.io.tmpdir");
        System.out.println(path);

        File folder1 = new File(path + "\\folder1");
        File folder2 = new File(path + "\\folder1\\folder2");
        File folder3 = new File(path + "\\folder1\\folder3");
        File folder4 = new File(path + "\\folder1\\folder2\\folder4");

        assertTrue(folder1.mkdir());
        assertTrue(folder2.mkdir());
        assertTrue(folder3.mkdir());
        assertTrue(folder4.mkdir());

        File file1 = new File(path + "\\folder1\\file1.txt");
        File file2 = new File(path + "\\folder1\\file2.txt");
        File file3 = new File(path + "\\folder1\\folder2\\file3.txt");
        File file4 = new File(path + "\\folder1\\folder3\\file4.ini");
        File file5 = new File(path + "\\folder1\\folder2\\folder4\\file5.ini");

        try {
            assertTrue(file1.createNewFile());
            assertTrue(file2.createNewFile());
            assertTrue(file3.createNewFile());
            assertTrue(file4.createNewFile());
            assertTrue(file5.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<File> result = new ArrayList<>();
        result.add(file5);
        result.add(file2);
        result.add(file1);
        result.add(file4);
        result.add(file3);

        List<String> exts = Arrays.asList(".txt", ".ini");

        assertTrue(result.removeAll(Search.files(path, exts)));
    }
}

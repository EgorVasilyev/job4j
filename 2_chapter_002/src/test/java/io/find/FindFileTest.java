package io.find;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FindFileTest {
    private String path = System.getProperty("java.io.tmpdir");
    private String fs = File.separator;
    private FindFile findFile;
    private Args args;
    @Before
    public void createFiles() throws IOException {
        System.out.println(path);

        File folder1 = new File(path + fs + "FindFileTest");
        File folder2 = new File(path + fs + "FindFileTest" + fs + "folder2");
        File folder3 = new File(path + fs + "FindFileTest" + fs + "folder3");
        File folder4 = new File(path + fs + "FindFileTest" + fs + "folder2" + fs + "folder4");

        folder1.mkdir();
        folder2.mkdir();
        folder3.mkdir();
        folder4.mkdir();

        File file1 = new File(path + fs + "FindFileTest" + fs + "file1.txt");
        File file2 = new File(path + fs + "FindFileTest" + fs + "file2.txt");
        File file3 = new File(path + fs + "FindFileTest" + fs + "folder2" + fs + "file3.txt");
        File file4 = new File(path + fs + "FindFileTest" + fs + "folder3" + fs + "file4.ini");
        File file5 = new File(path + fs + "FindFileTest" + fs + "folder2" + fs + "folder4" + fs + "file3.ini");

        file1.createNewFile();
        file2.createNewFile();
        file3.createNewFile();
        file4.createNewFile();
        file5.createNewFile();
    }
    @Test
    public void findByNameWithoutExt() throws IOException {
        args = new Args(path + fs + "FindFileTest", path + fs + "FindFileTest",
                "file3", false);
        findFile = new FindFile(args);
        findFile.files();
        BufferedReader br = new BufferedReader(new FileReader(path + fs + "FindFileTest" + fs + "foundFiles.txt"));
        String firstLine = br.readLine();
        String secondLine = br.readLine();
        assertThat(firstLine, is(path + "FindFileTest" + fs + "folder2" + fs + "file3.txt"));
        assertThat(secondLine, is(path + "FindFileTest" + fs + "folder2" + fs + "folder4" + fs + "file3.ini"));
    }
    @Test
    public void findByFullNameWithExt() throws IOException {
        args = new Args(path + fs + "FindFileTest", path + fs + "FindFileTest",
                "file3.txt", false);
        findFile = new FindFile(args);
        findFile.files();
        BufferedReader br = new BufferedReader(new FileReader(path + fs + "FindFileTest" + fs + "foundFiles.txt"));
        String firstLine = br.readLine();
        assertThat(firstLine, is(path + "FindFileTest" + fs + "folder2" + fs + "file3.txt"));
    }
    @Test
    public void findByEndMask() throws IOException {
        args = new Args(path + fs + "FindFileTest", path + fs + "FindFileTest",
                "file*", false);
        findFile = new FindFile(args);
        findFile.files();
        BufferedReader br = new BufferedReader(new FileReader(path + fs + "FindFileTest" + fs + "foundFiles.txt"));
        String firstLine = br.readLine();
        String secondLine = br.readLine();
        String thirdLine = br.readLine();
        String fourthLine = br.readLine();
        String fifthLine = br.readLine();
        assertThat(firstLine, is(path + "FindFileTest" + fs + "file1.txt"));
        assertThat(secondLine, is(path + "FindFileTest" + fs + "file2.txt"));
        assertThat(thirdLine, is(path + "FindFileTest" + fs + "folder2" + fs + "file3.txt"));
        assertThat(fourthLine, is(path + "FindFileTest" + fs + "folder3" + fs + "file4.ini"));
        assertThat(fifthLine, is(path + "FindFileTest" + fs + "folder2" + fs + "folder4" + fs + "file3.ini"));
    }
    @Test
    public void findByStartMask() throws IOException {
        args = new Args(path + fs + "FindFileTest", path + fs + "FindFileTest",
                "*.ini", false);
        findFile = new FindFile(args);
        findFile.files();
        BufferedReader br = new BufferedReader(new FileReader(path + fs + "FindFileTest" + fs + "foundFiles.txt"));
        String firstLine = br.readLine();
        String secondLine = br.readLine();
        assertThat(firstLine, is(path + "FindFileTest" + fs + "folder3" + fs + "file4.ini"));
        assertThat(secondLine, is(path + "FindFileTest" + fs + "folder2" + fs + "folder4" + fs + "file3.ini"));
    }
    @Test
    public void findByRegEx() throws IOException {
        args = new Args(path + fs + "FindFileTest", path + fs + "FindFileTest",
                "^.*[4].*$", true);
        findFile = new FindFile(args);
        findFile.files();
        BufferedReader br = new BufferedReader(new FileReader(path + fs + "FindFileTest" + fs + "foundFiles.txt"));
        String firstLine = br.readLine();
        String secondLine = br.readLine();
        assertThat(firstLine, is(path + "FindFileTest" + fs + "folder2" + fs + "folder4"));
        assertThat(secondLine, is(path + "FindFileTest" + fs + "folder3" + fs + "file4.ini"));
    }
}

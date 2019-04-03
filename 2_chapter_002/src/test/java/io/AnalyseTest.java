package io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AnalyseTest {
    String path = System.getProperty("java.io.tmpdir");
    String logPath = path + "\\server.log";
    String resultPath = path + "\\unavailable.csv";
    @Before
    public void createLogFile() {
        System.out.println(path);
        File server = new File(path + "\\server.log");
        try {
            assertTrue(server.createNewFile());
            FileWriter writer = new FileWriter(server);
            try (BufferedWriter bufferWriter = new BufferedWriter(writer)) {
                StringBuilder resultStringBuilder = new StringBuilder("200 10:11:12").append(System.lineSeparator())
                        .append("300 10:11:15").append(System.lineSeparator())
                        .append("400 11:12:31").append(System.lineSeparator())
                        .append("500 12:13:12").append(System.lineSeparator())
                        .append("200 13:22:54").append(System.lineSeparator())
                        .append("200 14:23:44").append(System.lineSeparator())
                        .append("400 15:54:11").append(System.lineSeparator())
                        .append("300 16:55:52").append(System.lineSeparator())
                        .append("500 17:57:48").append(System.lineSeparator())
                        .append("500 18:57:55").append(System.lineSeparator())
                        .append("400 19:58:48").append(System.lineSeparator())
                        .append("500 20:59:48").append(System.lineSeparator())
                        .append("200 21:33:36");
                bufferWriter.write(new String(resultStringBuilder));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void analyseTest() throws IOException {
        Analyse.unavailable(logPath, resultPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "\\unavailable.csv"));
        String line1 = bufferedReader.readLine();
        String line2 = bufferedReader.readLine();
        String line3 = bufferedReader.readLine();
        assertThat(line1, is("11:12:31;13:22:54;"));
        assertThat(line2, is("15:54:11;16:55:52;"));
        assertThat(line3, is("17:57:48;21:33:36;"));
    }
}

package io;

        import org.junit.Test;

import java.io.*;

        import static org.hamcrest.Matchers.is;
        import static org.junit.Assert.assertThat;
        import static org.junit.Assert.assertTrue;

public class AnalyseTest {
    @Test
    public void analyseTest() {
        String path = System.getProperty("java.io.tmpdir");
        System.out.println(path);
        String logPath = path + "\\log.txt";
        String resultPath = path + "\\result.txt";

        File log = new File(path + "\\log.txt");
        try {
            assertTrue(log.createNewFile());
            FileWriter writer = new FileWriter(log);
            try (BufferedWriter bufferWriter = new BufferedWriter(writer)) {

                StringBuilder resultStringBuilder = new StringBuilder("200 10:11:12")
                        .append(System.lineSeparator())
                        .append("300 10:11:15")
                        .append(System.lineSeparator())
                        .append("400 10:12:31")
                        .append(System.lineSeparator())
                        .append("500 10:13:12")
                        .append(System.lineSeparator())
                        .append("200 10:22:54")
                        .append(System.lineSeparator())
                        .append("200 10:23:44")
                        .append(System.lineSeparator())
                        .append("400 10:54:11")
                        .append(System.lineSeparator())
                        .append("300 10:55:52")
                        .append(System.lineSeparator())
                        .append("500 10:57:48")
                        .append(System.lineSeparator())
                        .append("500 10:57:55")
                        .append(System.lineSeparator())
                        .append("400 10:58:48")
                        .append(System.lineSeparator())
                        .append("500 10:59:48")
                        .append(System.lineSeparator())
                        .append("200 12:33:36");
                bufferWriter.write(new String(resultStringBuilder));
            }
            Analyse.unavailable(logPath, resultPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "\\result.txt"));
            assertThat(bufferedReader.readLine(), is("10:12:31;10:22:54"));
            assertThat(bufferedReader.readLine(), is("10:54:11;10:55:52"));
            assertThat(bufferedReader.readLine(), is("10:57:48;12:33:36"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

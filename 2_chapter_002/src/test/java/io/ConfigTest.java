package io;

import org.junit.Assert.*;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ConfigTest {
    @Test
    public void testMyConfig() {
        String path = System.getProperty("java.io.tmpdir");
        System.out.println(path);
        File testConfig = new File(path + "\\testConfig.txt");
        BufferedWriter bufferWriter = null;
        try {
            assertTrue(testConfig.createNewFile());
            FileWriter writer = new FileWriter(testConfig);
            bufferWriter = new BufferedWriter(writer);
            String testConfigString = "123=321\nasd=dsa\n//testComment\nKo9=9oK"
                    + "\n/*testComment2*/\nIP=192.168.2.3\ndate=02/04/2019";
            int index = 0;
            while (index != 7) {
                String line = testConfigString.split("\n")[index++];
                bufferWriter.write(line);
                bufferWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Config config = new Config(path + "\\testConfig.txt");
        config.load();
        assertThat(config.value("123"), is("321"));
        assertThat(config.value("date"), is("02/04/2019"));
        assertThat(config.value("Ko9"), is("9oK"));
        assertThat(config.value("asd"), is("dsa"));
        assertThat(config.value("IP"), is("192.168.2.3"));
    }
}

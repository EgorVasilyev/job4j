package io;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ConfigTest {
    String path = System.getProperty("java.io.tmpdir");
    @Before
    public void createTestConfigFile() throws IOException {
        System.out.println(path);
        File testConfig = new File(path + "\\testConfig.txt");
        assertTrue(testConfig.createNewFile());
        FileWriter writer = new FileWriter(testConfig);
        BufferedWriter bufferWriter = new BufferedWriter(writer);
        String testConfigString = "123=321" + System.lineSeparator()
                + "asd=dsa" + System.lineSeparator()
                + "//testComment" + System.lineSeparator()
                + "Ko9=9oK" + System.lineSeparator()
                + "/*testComment2*/" + System.lineSeparator()
                + "IP=192.168.2.3" + System.lineSeparator()
                + "date=02/04/2019";
        bufferWriter.write(testConfigString);
        bufferWriter.close();
    }
    @Test
    public void testMyConfig() {
        Config config = new Config(path + "\\testConfig.txt");
        config.load();
        assertThat(config.value("123"), is("321"));
        assertThat(config.value("date"), is("02/04/2019"));
        assertThat(config.value("Ko9"), is("9oK"));
        assertThat(config.value("asd"), is("dsa"));
        assertThat(config.value("IP"), is("192.168.2.3"));
    }
}

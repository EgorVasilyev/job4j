package io.bot;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    String path = System.getProperty("java.io.tmpdir");
    @Before
    public void createBotOracleText() throws IOException {
        System.out.println(path);
        File botOracle = new File(path + File.separator + "botOracle.txt");
        assertTrue(botOracle.createNewFile());
        FileWriter writer = new FileWriter(botOracle);
        BufferedWriter bufferWriter = new BufferedWriter(writer);
        String botOracleString = "привет___Приветствую" + System.lineSeparator()
                + "как дела?___В порядке" + System.lineSeparator()
                + "сколько тебе лет?___Много" + System.lineSeparator()
                + "что делаешь?___Думаю";
        bufferWriter.write(botOracleString);
        bufferWriter.close();
    }
    @Test
    public void serverTest() throws IOException {
        String inputString = String.format(
                "привет%sкак дела?%sвыход",
                System.lineSeparator(),
                System.lineSeparator()
        );
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket, path + File.separator + "botOracle.txt");
        server.runServer();
        assertThat(out.toString(), is(
                String.format(
                        "Приветствую%s%sВ порядке%s%s",
                        System.lineSeparator(),
                        System.lineSeparator(),
                        System.lineSeparator(),
                        System.lineSeparator()
                )
                )
        );
    }
}

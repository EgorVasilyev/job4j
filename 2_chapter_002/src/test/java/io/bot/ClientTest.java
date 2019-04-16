package io.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    String ln = System.lineSeparator();
    @Test
    public void clientTest() throws IOException {
        String consoleInput = String.format(
                "привет%sвыход",
                System.lineSeparator()
        );
        String serverInput = new StringJoiner(ln, "Приветствую", ln).add("").add("").add("").toString();

        Socket socket = mock(Socket.class);

        ByteArrayOutputStream consoleOutputStream = new ByteArrayOutputStream();
        PrintStream consolePrintStream = new PrintStream(consoleOutputStream);
        System.setOut(consolePrintStream);
        ByteArrayInputStream consoleInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(consoleInputStream);

        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(serverInput.getBytes()));
        when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());

        Client client = new Client(socket);
        client.runClient();

        String expected = "Приветствую" + ln;
        assertThat(consoleOutputStream.toString(), is(expected));
        System.setIn(System.in);
        System.setOut(System.out);
    }
}

package io.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    @Test
    public void clientTest() throws IOException {
        String consoleInput = String.format(
                "привет%sвыход",
                System.lineSeparator()
        );
        String serverInput = String.format(
                "Приветствую%s%s",
                System.lineSeparator(),
                System.lineSeparator()
        );
        Socket socket = mock(Socket.class);

        ByteArrayOutputStream consoleOutputStream = new ByteArrayOutputStream();
        PrintStream consolePrintStream = new PrintStream(consoleOutputStream);
        System.setOut(consolePrintStream);
        ByteArrayInputStream consoleInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(consoleInputStream);

        when(socket.getInputStream()).thenReturn( new ByteArrayInputStream(serverInput.getBytes()));
        when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());

        Client client = new Client(socket);
        client.runClient();

        String expected = String.format(
                "привет%sПриветствую%sвыход%s",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator()
        );

        assertThat(consoleOutputStream.toString(), is(expected));
        System.setIn(System.in);
        System.setOut(System.out);
    }
}

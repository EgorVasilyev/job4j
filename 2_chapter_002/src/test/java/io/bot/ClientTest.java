package io.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientTest {
    @Test
    public void clientTest() throws IOException {
        //эмулируем ввод с консоли
        String consoleInput = String.format(
                "привет%sвыход",
                System.lineSeparator()
        );
        ByteArrayInputStream consoleInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(consoleInputStream);

        //ловим ответ
        ByteArrayOutputStream consoleOutputStream = new ByteArrayOutputStream();
        PrintStream consolePrintStream = new PrintStream(consoleOutputStream);
        System.setOut(consolePrintStream);

        Socket socket = mock(Socket.class);

        String serverInput = String.format(
                "Приветствую%s%s",
                System.lineSeparator(),
                System.lineSeparator()
        );
        ByteArrayInputStream in = new ByteArrayInputStream(serverInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //serverInput это то что должен вернуть сервер

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket);
        client.runClient();
        assertThat(out.toString(), is(consoleInput));
        System.setIn(System.in);
        System.setOut(System.out);
    }
}

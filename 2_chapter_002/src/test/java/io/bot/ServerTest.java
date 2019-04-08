package io.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    @Test
    public void name() throws IOException {
        String inputString = "привет" + System.lineSeparator()
                + "как дела?" + System.lineSeparator()
                + "выход";
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.runServer();
        assertThat(out.toString(), is("Приветствую" + System.lineSeparator() + System.lineSeparator()
                + "В порядке" + System.lineSeparator() + System.lineSeparator()));
    }
}

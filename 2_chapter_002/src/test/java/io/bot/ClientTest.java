package io.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    @Test
    public void clientTest() throws IOException {
        String emulateConsoleInput = String.format(
                "привет%sкак дела?%sвыход",
                System.lineSeparator(),
                System.lineSeparator()
        );
        String lineFromServer = String.format(
                "Приветствую%s%sВ порядке%s%s",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator()
        );
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(lineFromServer.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket, emulateConsoleInput);
        client.runClient();
/*        assertThat(out.toString(), is(

                )
        );*/
    }
}

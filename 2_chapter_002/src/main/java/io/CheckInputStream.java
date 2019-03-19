package io;

import java.io.IOException;
import java.io.InputStream;

public class CheckInputStream {
    public static boolean isEvenNumber(InputStream in) throws IOException {
        boolean result = true;
        try {
            int value = 0;
            while (value != -1) {
                value = in.read();
                if (value != -1 && (value == 0 || value % 2 != 0)) {
                    result = false;
                    break;
                }
            }
        } finally {
            in.close();
        }
        return result;
    }
}

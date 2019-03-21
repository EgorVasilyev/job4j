package io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CheckInputStream {
    public static boolean isEvenNumber(InputStream in) throws IOException {
        boolean result = true;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int value;
            int finishValue = 0;
            while ((value = in.read()) != -1) {
                out.reset();
                finishValue = value;
                out.write(finishValue);
                if (!Character.isDigit(out.toString().charAt(0))) {
                    result = false;
                    break;
                }
            }
            if (result && !(Integer.parseInt(out.toString()) % 2 == 0)) {
                result = false;
            }
        } finally {
            in.close();
            out.close();
        }
        return result;
    }
}

package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 1
 * @since 29.11.2018
 */

public class IteratorEven implements Iterator {

    private final int[] array;
    private int index = 0;
    private int count = 0;

    public IteratorEven(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = count; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        count++;
        while (array[index] % 2 != 0) {
            index++;
            count++;
        }
        return array[index++];
    }
}
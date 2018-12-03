package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 2
 * @since 03.12.2018
 */


public class IteratorEven implements Iterator {

    private final int[] array;
    private int index = 0;

    public IteratorEven(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        int count = 0;
        while (index + count < array.length) {
            if (array[index + count] % 2 == 0) {
                index = index + count;
                return true;
            }
            count++;
        }
        return false;
    }

    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}

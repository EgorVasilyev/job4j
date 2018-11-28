package iterator;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 1
 * @since 29.11.2018
 */

public class IteratorForArray implements Iterator {

    private final int[][] array;
    private int size = 0;
    private int row = 0;
    private int cell = 0;
    private int count = 0;

    public IteratorForArray(int[][] array) {
        this.array = array;
        Arrays.stream(this.array)
                .forEach(inArray -> Arrays.stream(inArray)
                        .forEach(x -> size++));
    }

    @Override
    public boolean hasNext() {
        return count < size;
    }

    public Object next() {
        count++;
            if (cell > array[row].length - 1) {
                row++;
                cell = 0;
            }
        return array[row][cell++];
    }
}
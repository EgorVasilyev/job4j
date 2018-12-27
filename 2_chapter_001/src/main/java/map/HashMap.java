package map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Iterable<HashMap.Entry<K, V>>  {

    private int capacity = 16;
    private Entry[] container = new Entry[capacity];
    private int index  = 0;
    private int countMod = 0;

    /**
     * Simple class for save pair of key-value in the map
     * @param <K>
     * @param <V>
     */
    public static class Entry<K, V> {
        public K key;
        public V value;

        public Entry(K k, V v) {
            key = k;
            value = v;
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * and insert pair if key is not exist
     * @param key
     * @param value
     * @return
     */
    public boolean insert(K key, V value) {
        int newIndex = getIndex(key);
        boolean result = container[newIndex] == null;
        if (result) {
            container[newIndex] = new Entry(key, value);
            index++;
            countMod++;
        }
        return result;
    }

    /**
     * Get index based key
     * @param key
     * @return index
     */
    private int getIndex(K key) {
        return key.hashCode() & (capacity - 1);
    }


    /**
     * Returns the value to which the specified key is mapped,
     *or null if this map contains no mapping for the key.
     * @param key
     * @return
     */
    public V get(K key) {
        V result = null;
        int foundIndex = getIndex(key);
        Entry<K, V> pair = container[foundIndex];
        if (pair != null && pair.key.equals(key)) {
            result = (V) container[foundIndex].value;
        }
        return result;
    }


    /**
     * Deletes the specified value with the specified key in this map.
     * @param key
     * @return
     */
    public boolean delete(K key) {
        boolean result = false;
        int foundIndex = getIndex(key);
        Entry<K, V> pair = container[foundIndex];
        if (pair != null) {
            container[foundIndex] = null;
            result = true;
            index--;
            countMod++;
        }
        return result;
    }

    private void checkCAPACITY() {
        if (index >= capacity * 0.75) {
            capacity *= 2;
            Entry[] second = new Entry[capacity];
            //перехеширование элементов
            for (Entry pair : container) {
                second[getIndex((K) pair.key)] = pair;
            }
            container = Arrays.copyOf(second, capacity);
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     * @return
     */
    public int getSize() {
        return index;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {

        return new Iterator<Entry<K, V>>() {

            int checkCountMod = countMod;
            int count = 0;

            @Override
            public boolean hasNext() {
                if (checkCountMod != countMod) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                int hasNextCount = count;
                while (hasNextCount < capacity) {
                    if (container[hasNextCount] != null) {
                        result = true;
                        break;
                    }
                    hasNextCount++;
                }
                return result;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }

        };
    }
}
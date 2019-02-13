package monitor;

import list.DinamicContainer;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.Iterator;

/**
 * Класс - многопоточная коллекция данных.
 */
@ThreadSafe
public class ThreadSafeDinamicContainer<T> implements Iterable<T>, Cloneable {
    @GuardedBy("this")
    private DinamicContainer<T> array;

    public ThreadSafeDinamicContainer() {
        this.array = new DinamicContainer<T>();
    }
    /**
     * Добавление элемента.
     * @param model новый элемент.
     */
    public synchronized void add(T model) {
        this.array.add(model);
    }
    /**
     * Получение элемента по индексу.
     * @param index индекс.
     * @return элемент типа Т.
     */
    public synchronized T get(int index) {
        return this.array.get(index);
    }
    /**
     * Итератор копии коллекции.
     * @return Итератор.
     */
    @Override
    public synchronized Iterator<T> iterator() {
        return copy().iterator();
    }
    /**
     * Ссздает копию коллекции.
     * @return Копия коллекции.
     */
    private synchronized DinamicContainer<T> copy() {
        DinamicContainer<T> copyArray = new DinamicContainer<>();
        for (T value : this.array) {
            copyArray.add(value);
        }
        return copyArray;
    }
}

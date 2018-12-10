package generic;

import java.util.Iterator;

/**
 * @author Egor
 * @version 1
 * @since 10.12.2018
 */

public class AbstractStore<T extends Base> implements Store<T> {
    /**
     * Хранилище для объектов типа Т.
     */
    private final SimpleArray<T> array;
    /**
     * Размер хранилища.
     */
    private final int size;
    /**
     * Конструктор.
     */
    public AbstractStore(int size) {
        this.size = size;
        this.array = new SimpleArray<T>(size);
    }
    /**
     * Добавление объекта типа Т в хранилище.
     */
    @Override
    public void add(T model) {
        if (model != null) {
            array.add(model);
        }
    }
    /**
     * Замена объекта типа Т по Id.
     * return результат замены да/нет.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        if (model != null) {
            for (int i = 0; i < this.size; i++) {
                if (array.get(i) != null && array.get(i).getId().equals(id)) {
                    array.set(i, model);
                    result = true;
                }
            }
        }
        return result;
    }
    /**
     * Удаление объекта типа Т по Id.
     * return результат удаления да/нет.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < this.size; i++) {
            if (array.get(i) != null && array.get(i).getId().equals(id)) {
                array.delete(i);
                result = true;
            }
        }
        return result;
    }
    /**
     * Поиск объекта типа Т по Id.
     * return объект типа Т.
     */
    @Override
    public T findById(String id) {
        T resultUser = null;
        for (int i = 0; i < this.size; i++) {
            if (array.get(i) != null && array.get(i).getId().equals(id)) {
                resultUser = array.get(i);
            }
        }
        return resultUser;
    }
    /**
     * return Итератор хранилища.
     */
    @Override
    public Iterator<T> iter() {
        return array.iterator();
    }
}

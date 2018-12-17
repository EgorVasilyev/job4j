package set;

import list.DinamicContainer;

import java.util.Iterator;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 1
 * @since 17.12.2018
 */

public class SimpleSet<T> implements Iterable<T> {
    private DinamicContainer<T> container;

    /**
     * Конструктор.
     */
    public SimpleSet() {
        this.container = new DinamicContainer<T>();
    }

    /**
     * Добавление элемента c проверкой на дубликаты.
     * @param model новый элемент.
     */
    public boolean add(T model) {
        boolean modelFound = checkDuplicate(model);
        if (!modelFound) {
            this.container.add(model);
        }
        return !modelFound;
    }

    private boolean checkDuplicate(T model) {
        boolean modelFound = false;
        if (this.container != null) {
            for (Object intModel : this.container) {
                if (model.equals((T) intModel)) {
                    modelFound = true;
                    break;
                }
            }
        }
        return modelFound;
    }

    /**
     * Итератор.
     * @return Итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return this.container.iterator();
    }
}
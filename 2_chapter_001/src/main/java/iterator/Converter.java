package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Vladimir Ivanov
 * @version 1
 * @since 04.12.2018
 */
public class Converter {
    /**
     * Конвертация Iterator<Iterator<Integer>> в Iterator<Integer>.
     * @param it Итератор итераторов.
     * @return Итератор чисел типа Integer.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            /**
             * Создаем текущий итератор.
             * Если есть внутренний итератор, то используем его.
             * Если нет, то присваиваем значение null.
             */
            private Iterator<Integer> current = it.hasNext() ? it.next() : null;

            /**
             * реализация hasNext().
             * @return Если есть элементы в текущем итераторе, то true.
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                while (this.current != null) {
                    if (this.current.hasNext()) {
                        result = true;
                        break;
                    } else {
                        this.current = (it.hasNext()) ? it.next() : null;
                    }
                }
                return result;
            }

            /**
             * реализация next().
             * @return next Integer.
             */
            @Override
            public Integer next() throws NoSuchElementException {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return this.current.next();
            }
        };
    }
}
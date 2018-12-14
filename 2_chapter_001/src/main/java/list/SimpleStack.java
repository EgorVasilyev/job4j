package list;
/**
 * @author Egor
 * @version 1
 * @since 13.12.2018
 */
public class SimpleStack<T> {

    private DinamicContainerLL<T> stack = new DinamicContainerLL<>();

    /**
     * Метод вставляет в начало списка данные.
     */
    public void push(T date) {
        if (date != null) {
            stack.add(date);
        }
    }

    /**
     * Метод возвращает первое значение из связанного списка и удаляет его.
     */
    public T poll() {
        return stack.delete();
    }

    /**
     * Обнуление размера списка.
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

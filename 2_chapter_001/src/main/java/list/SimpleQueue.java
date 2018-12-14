package list;
/**
 * @author Egor
 * @version 1
 * @since 13.12.2018
 */
public class SimpleQueue<T> {

    private SimpleStack<T> stackOne = new SimpleStack<>();
    private SimpleStack<T> stackTwo = new SimpleStack<>();;
    private int count;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void push(T date) {
        if (date != null) {
            stackOne.push(date);
            count++;
        }
    }

    /**
     * Метод возвращает последнее значение из связанного списка и удаляет его.
     * First input first output
     */
    public T poll() {
        if (stackTwo.isEmpty()) {
            for (int i = 0; i < count; i++) {
                stackTwo.push(stackOne.poll());
            }
        }
        count--;
        return stackTwo.poll();
    }
}

package list;
/**
 * @author Egor
 * @version 1
 * @since 13.12.2018
 */
public class SimpleQueue<T> {

    private DinamicContainerLL<T> stackOne = new DinamicContainerLL<>();
    private DinamicContainerLL<T> stackTwo = new DinamicContainerLL<>();;
    private int count;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void push(T date) {
        if (date != null) {
            stackOne.add(date);
            count++;
        }
    }

    /**
     * Метод возвращает последнее значение из связанного списка и удаляет его.
     * First input first output
     */
    public T poll() {
        for (int i = 0; i < count; i++) {
            stackTwo.add(stackOne.get(i));
        }
        T result = stackTwo.delete();
        //обнуляем первый стек
        stackOne = new DinamicContainerLL<>();
        //обновляем первый стек (без первого элемента)
        for (int j = 0; j < count - 1; j++) {
            stackOne.add(stackTwo.delete());
        }
        count--;
        return result;
    }
}

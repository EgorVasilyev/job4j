package thread;

import org.junit.Test;

public class MyThreadTest {
    @Test
    public void testProblemOfThread() throws InterruptedException {
/**
 * В данном примере мы создаем класс ресурс в котором имется переменная i. Также в классе есть метод changeI, который
 * просто добавляет к i единицу.  В методе майн мы создаем 1 объект ресурс (и в конструкторе инициализируем i = 0),
 * и 8 объектов MyThread, в конструкторы которых передаем наш единственный объект resourse. Затем стартуем все потоки.
 * По логике вещей в итоговом результате на консоль должно было быть выведено 8.  Однако результат будет разный.
 * Это связано с тем, что переменная i может быть сохранена в кэше, а не в омновной памяти. А другой поток
 * может взять и обратиться в основную память, в которой результат еще не обновлен.
 */
        Resource resource = new Resource(0);

        MyThread thread1 = new MyThread(resource);
        MyThread thread2 = new MyThread(resource);
        MyThread thread3 = new MyThread(resource);
        MyThread thread4 = new MyThread(resource);
        MyThread thread5 = new MyThread(resource);
        MyThread thread6 = new MyThread(resource);
        MyThread thread7 = new MyThread(resource);
        MyThread thread8 = new MyThread(resource);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

        System.out.println(resource.getI());
    }
}
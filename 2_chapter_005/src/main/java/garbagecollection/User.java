package garbagecollection;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private String passport;
    private String name;

    public static final AtomicInteger COUNT = new AtomicInteger();

    public User(String passport, String name) {
        COUNT.incrementAndGet();
        System.out.println(User.COUNT);
        this.passport = passport;
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
       // System.out.println("finalize()");
    }

    public static void main(String[] args) {
        /**
         * Ручной подсчет:
         */
        User user1 = new User("pass1", "name1");
        /**
         * http://jug.ua/wp-content/uploads/2013/02/Java.pdf
         *
         * Заколовок объекта класса:
         * 32-х разрядная система - 8 байт
         * 64-х разрядная система - 16 байт
         *
         * Размеры примитивов:
         *
         * byte    1 byte
         * short   2 byte
         * int     4 byte
         * long    8 byte
         * char    2 byte
         * float   4 byte
         * double  8 byte
         * boolean:
         * Размер логического типа полностью зависит от Вашей JVM.
         * Например, в Oracle HotSpot JVM под логический тип
         * выделяется 4 байта, то есть столько же сколько и под int. За
         * хранение 1 бита информации Вы платите 31 битом.
         *
         * Строка "pass1":
         *
         * Смотрим поля в классе String:
         * Заголовок: 16 байт
         * private final char value[]; - 4 байта(Ссылочная переменная на объект массива:)
         * private final int offset; - 4 байта(int)
         * private final int count; - 4 байта(int)
         * private int hash; - 4 байта(int)
         * Выравнивание для кратности '8' не нужно.
         * Итого: 32 байта
         *
         * Так как строка содержит ссылку на массив символов,
         * то, по сути, мы имеем дело с двумя разными объектами —
         * объектом класса String и самим массивом, который хранит строку.
         * А это еще 20 байт на сам объект массива + 2 байта на каждый символ строки.
         *
         * массив char[]:
         *
         * Заголовок: 16 байт + 4 байта на длину массива == 20 байт
         * Примитивы char: 2 байта * 5 == 10 байта
         * Выравнивание для кратности 8 : 2 байта
         * Итого: 32 байта
         *
         * Общий размер: 64 байта
         *
         * Строка "name1":
         *
         * Смотрим поля в классе String:
         * -//-
         * Итого: 32 байта
         *
         * массив char[]:
         *
         * Заголовок: 16 байт + 4 байта на длину массива == 20 байт
         * Примитивы char: 2 байта * 5 == 10 байта
         * Выравнивание для кратности 8 : 2 байта
         * Итого: 32 байта
         *
         * Общий размер: 64 байта
         *
         * Система 64-х разрядная, размер заголовка экземпляра класса 16 байт
         *
         * Общий размер объекта: 64 + 64 + 16 = 144 байта
         */
        User user2 = new User("passp2", "name2");
        /**
         * По аналогии получаем 144 байта
         */
        User user3 = new User("passpo3", "name3");
        /**
         * По аналогии получаем 152 байтов
         */

        /**
         * Попытка вызвать намеренно вызвать GC за счет указания парметра VM -Xmx1m
         */
        for (int i = 0; i < 1000000; i++) {
            new User("pass1", "name1");
        }

    }
}
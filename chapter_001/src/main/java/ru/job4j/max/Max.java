package ru.job4j.max;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class Max {
    /**
     * Находит максимальное значение из двух чисел.
     * @param first Первое число.
     * @param second Второе число.
     * @return Максимальное значение.
     */
    public int max(int first, int second){
        return (first>second?first:second);
    }
    /**
     * Находит максимальное значение из трех чисел.
     * @param first Первое число.
     * @param second Второе число.
     * @param third Третье число.
     * @return Максимальное значение.
     */
    public int maxThree(int first, int second, int third){
        int temp = this.max(first, second);
        temp = this.max(temp, third);
        return temp;
    }
}

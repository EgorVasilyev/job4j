package ru.job4j.loop;
/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Fitness {
    /**
     * Метод вычисляет количество месяцев, которое необходимо Ивану, чтобы обогнать в тяге Николая.
     *
     * @param ivan - тяга Ивана.
     * @param nik - тяга Николая.
     * @return количество месяцев.
     */
    public int calc(int ivan, int nik) {
        int month = 0;
        while (ivan < nik) {
            month++;
            ivan *= 3;
            nik *= 2;
        }
        return month;
    }
}

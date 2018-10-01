package ru.job4j.calculator;

/**
 * Программа-элементарный калькулятор.
 */
public class Calculator {
    private double result;
    /**
     * Метод сложения чисел.
     * @param first Первое число.
     * @param second Второе число.
     * Получаем результат сложения.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }
    /**
     * Метод вычитания чисел.
     * @param first Первое число.
     * @param second Второе число.
     * Получаем результат вычитания.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }
    /**
     * Метод деления чисел.
     * @param first Первое число.
     * @param second Второе число.
     * Получаем результат деления.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }
    /**
     * Метод умножения чисел.
     * @param first Первое число.
     * @param second Второе число.
     * Получаем результат умножения.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }
    /**
     * Метод возврата результата.
     * @return результат действия.
     */
    public double getResult() {
        return this.result;
    }
}
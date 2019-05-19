package ru.job4j.solid.srp;

import ru.job4j.solid.ocp.EngineerCalculator;

/**
 * InteractCalc - интерактивный калькулятор
 */
public class InteractCalc {
    /**
     * Калькулятор
     */
    private final Calculator calculator;
    /**
     * Первое число
     */
    private double first;
    /**
     * Второе число
     */
    private double second;
    /**
     * Результат вычисления
     */
    private double result;
    /**
     * Булева переменная, показывающая, что одно вычисление уже было выполнено
     */
    private boolean notFirstAction;
    /**
     * Булева переменная для заверения программы
     */
    private boolean exitCalc;
    /**
     * Булева переменная, показывающая отсутствие значения переменной
     */
    private boolean valueIsAbsent;
    /**
     * Символ последней используемой операции
     */
    private String lastActionSymbol;
    /**
     * Способ ввода информации
     */
    private final Input input;

    /**
     * Конструктор
     * @param calculator - калькулятор
     * @param input - способ ввода
     */
    public InteractCalc(final Calculator calculator, final Input input) {
        this.calculator = calculator;
        this.input = input;
        this.notFirstAction = false;
        this.valueIsAbsent = true;
    }

    /**
     * Выводит на консоль результат вычсиления
     */
    private void showResult() {
        System.out.println("Результат: " + this.result);
        checkExit();
    }

    /**
     * Ввод значений и операции
     */
    public void input() {
        do {
            this.first = checkInput(this.input.askString("Введите первое число (или \"e\" для выхода, или \"p\" "
                    + "для использования предыдущего вычисления):"));
            if (this.exitCalc) {
                return;
            }
            this.second = checkInput(this.input.askString("Введите второе число (или \"e\" для выхода, или \"p\" "
                    + "для использования предыдущего вычисления):"));
            if (this.exitCalc) {
                return;
            }
            this.showActions();
            this.result = chooseAction(this.input.askString("Введите символ операции "
                    + "(или \"l\" для использования предыдущей операции, "
                    + "или \"e\" для выхода):"));
            if (this.exitCalc) {
                return;
            }
            this.showResult();
            this.notFirstAction = true;
        } while (!this.exitCalc);
    }

    /**
     * Проверка на выход из программы и использование предыдущего значения
     * @param in - введенное значение
     * @return число
     */
    private double checkInput(String in) {
        valueIsAbsent = true;
        double value = -1;
        if (in.equals("e")) {
            this.exitCalc = true;
        } else {
            if (in.equals("p")) {
                if (this.notFirstAction) {
                    value = this.result;
                    System.out.println("Используем предыдущий результат: " + value);
                } else {
                    System.out.println("Предыдущий результат отсутствует!");
                    value = checkInput(this.input.askString("Введите число (или \"e\" для выхода):"));
                }
            } else {
                try {
                    value = this.input.askDouble(in);
                } catch (NumberFormatException nfe) {
                    value = checkInput(this.input.askString(
                            String.format("'%s' не является числом. Введите число (или \"e\" для выхода, или \"p\" "
                                    + "для использования предыдущего вычисления):", in))
                    );
                }
            }
        }
        return value;
    }
    /**
     * Проверка на выход из программы
     */
    private void checkExit() {
        System.out.println("Для выхода из программы введите \"e\"");
        System.out.println("или любой другой символ для продолжения вычислений:");
        if (this.input.next().toLowerCase().equals("e")) {
            this.exitCalc = true;
        }
    }
    /**
     * Проверка на выход из программы и использование предыдущей операции
     * @param symbol - введенное значение
     * @return результат вычисления
     */
    private double chooseAction(String symbol) {
        double value = -1;
        if (symbol.equals("e")) {
            this.exitCalc = true;
        } else {
            if (symbol.equals("l")) {
                if (this.notFirstAction) {
                    try {
                        value = calculator.getResult(this.first, this.second, lastActionSymbol);
                    } catch (CalculateException e) {
                        System.out.println(e.getMessage());
                        this.showActions();
                        value = chooseAction(this.input.askString("Введите другую доступную операцию "
                                + "(или \"l\" для использования предыдущей операции, "
                                + "или \"e\" для выхода):"));
                    }
                } else {
                    System.out.println("Предыдущая операция отсутствует!");
                    this.showActions();
                    value = chooseAction(this.input.askString("Введите символ операции "
                            + "(или \"e\" для выхода):"));
                }
            } else {
                do {
                    valueIsAbsent = true;
                    if (calculator.getActions().containsKey(symbol)) {
                        try {
                            value = calculator.getResult(this.first, this.second, symbol);
                            valueIsAbsent = false;
                            lastActionSymbol = calculator.getActions().get(symbol).getSymbol();
                        } catch (CalculateException e) {
                            System.out.println(e.getMessage());
                            this.showActions();
                            value = chooseAction(this.input.askString("Введите другую доступную операцию "
                                    + "(или \"l\" для использования предыдущей операции, "
                                    + "или \"e\" для выхода):"));
                        }
                    } else {
                        System.out.printf("Символ '%s' не является операцией! ", symbol);
                        this.showActions();
                        value = chooseAction(this.input.askString("Введите доступную операцию "
                                + "(или \"l\" для использования предыдущей операции, "
                                + "или \"e\" для выхода):"));
                    }
                } while (!this.exitCalc && valueIsAbsent);
            }
        }
        return value;
    }

    /**
     * Вывод на консоль всех доступных операций
     */
    private void showActions() {
        System.out.println("Доступны следующие операции:");
        for (Action action : this.calculator.getActions().values()) {
            System.out.println(action.getSymbol() + "  " + action.description());
        }
    }

    public static void main(String[] args) {
        InteractCalc intCalc = new InteractCalc(new EngineerCalculator(), new ValidateInputConsole());
        intCalc.input();
    }
}

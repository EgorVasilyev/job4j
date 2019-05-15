package ru.job4j.solid.srp;

import ru.job4j.calculator.Calculator;

import java.util.HashMap;
import java.util.Scanner;

public class InteractCalc {
    private final Calculator calculator;
    private double first;
    private double second;
    private double result;
    private boolean notFirstAction;
    private boolean exitCalc;
    private boolean isAction;
    private Action lastAction;
    private HashMap<String, Action> actions;


    public InteractCalc(final Calculator calculator) {
        this.calculator = calculator;
        this.notFirstAction = false;
        this.actions = new HashMap<>(10);
        this.createActions();
    }

    private void createActions() {
        String add = "+";
        this.actions.put(add, () -> {
            this.calculator.add(this.first, this.second);
            this.showResult(add);
        });
        String subtract = "-";
        this.actions.put(subtract, () -> {
            this.calculator.subtract(this.first, this.second);
            this.showResult(subtract);
        });
        String multiple = "*";
        this.actions.put(multiple, () -> {
            this.calculator.multiple(this.first, this.second);
            this.showResult(multiple);
        });
        String div = "/";
        this.actions.put(div, () -> {
            this.calculator.div(this.first, this.second);
            this.showResult(div);
        });
        String exit = "e";
        this.actions.put(exit, () -> {
            this.isAction = true;
            this.exitCalc = true;
        });
    }

    private void showResult(String action) {
        this.isAction = true;
        this.notFirstAction = true;
        this.result = calculator.getResult();
        System.out.println("Результат: " + this.first + " " + action + " " + this.second + " = " + this.result);
    }

    public void input() {
        Scanner scanner;
        do {
            scanner = new Scanner(System.in);
            this.first = this.checkNumber(scanner);
            if (this.exitCalc) {
                break;
            }
            scanner = new Scanner(System.in);
            this.second = this.checkNumber(scanner);
            if (this.exitCalc) {
                break;
            }
            scanner = new Scanner(System.in);
            this.chooseAction(scanner);
            if (this.exitCalc) {
                break;
            }
            scanner = new Scanner(System.in);
            this.checkExit(scanner);
        } while (!this.exitCalc);
    }

    private double checkNumber(Scanner scanner) {
        double number = 0;
        boolean isDouble;
        do {
            System.out.println("Введите число (или \"e\" для выхода, или \"p\" "
                    + "для использования предыдущего вычисления): ");
            isDouble = scanner.hasNextDouble();
            if (!isDouble) {
                String command = scanner.nextLine().toLowerCase();
                boolean usePrevResult = command.equals("p");
                if (usePrevResult) {
                    if (this.notFirstAction) {
                        isDouble = true;
                        number = this.result;
                    } else {
                        System.out.println("Предыдущий результат отсутствует!");
                    }
                }
                this.exitCalc = command.equals("e");
                if (!this.exitCalc && !usePrevResult) {
                    System.out.println("Введенный символ не является числом или командой.");
                }
            } else {
                number = scanner.nextDouble();
            }
        } while (!this.exitCalc && !isDouble);
        return number;
    }

    private void checkExit(Scanner scanner) {
        System.out.println("Для выхода из программы введите \"e\".");
        System.out.println("Для продолжения вычислений любой другой символ.");
        if (scanner.nextLine().toLowerCase().equals("e")) {
            this.exitCalc = true;
        }
    }

    private void chooseAction(Scanner scanner) {
        this.isAction = false;
        do {
            System.out.println("Введите действие: "
                    + "\n + сложение," + "\n - вычитание,"
                    + "\n * умножение," + "\n / деление "
                    + "\n(или \"e\" для выхода, или \"l\" для повторного использования последнего действия):");
            if (scanner.hasNext()) {
                String action = scanner.next();
                if (action.equals("l")) {
                    if (this.notFirstAction) {
                        this.lastAction.execute();
                    } else {
                        System.out.println("Предыдущее действие отсутствует!");
                    }
                } else {
                    this.lastAction = this.actions.getOrDefault(action, () -> { });
                    this.lastAction.execute();
                }
                if (!this.isAction) {
                    System.out.println("Введенный символ не является действием.");
                }
            }
        } while (!this.exitCalc && !this.isAction);
    }

    public static void main(String[] args) {
        InteractCalc intCalc = new InteractCalc(new Calculator());
        intCalc.input();
    }
}

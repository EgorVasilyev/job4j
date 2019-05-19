package ru.job4j.solid.srp;

import java.util.HashMap;

public class Calculator {
    private HashMap<String, Action> actions;

    public Calculator() {
        this.actions = new HashMap<>(10);
        this.addActions();
    }

    private void addActions() {
        Addition addition =  new Addition();
        Subtraction subtraction = new Subtraction();
        Multiplication multiplication = new Multiplication();
        Division division = new Division();
        this.actions.put(addition.getSymbol(), addition);
        this.actions.put(subtraction.getSymbol(), subtraction);
        this.actions.put(multiplication.getSymbol(), multiplication);
        this.actions.put(division.getSymbol(), division);
    }

    public HashMap<String, Action> getActions() {
        return this.actions;
    }

    public double getResult(double first, double second, String symbol) throws CalculateException {
       return this.actions.get(symbol).calculate(first, second);
    }

    protected void showActions() {
        for (Action action : this.actions.values()) {
            System.out.println(action.getSymbol() + "  " + action.description());
        }
    }

    private class Addition implements Action {

        @Override
        public String getSymbol() {
            return "+";
        }

        @Override
        public String description() {
            return "  Сложение";
        }

        @Override
        public double calculate(double first, double second) {
            return first + second;
        }
    }

    private class Subtraction implements Action {

        @Override
        public String getSymbol() {
            return "-";
        }

        @Override
        public String description() {
            return "  Вычитание";
        }

        @Override
        public double calculate(double first, double second) {
            return first - second;
        }
    }

    private class Multiplication implements Action {

        @Override
        public String getSymbol() {
            return "*";
        }

        @Override
        public String description() {
            return "  Умножение";
        }

        @Override
        public double calculate(double first, double second) {
            return first * second;
        }
    }

    private class Division implements Action {

        @Override
        public String getSymbol() {
            return "/";
        }

        @Override
        public String description() {
            return "  Деление";
        }

        @Override
        public double calculate(double first, double second) throws CalculateException {
            if (second == 0) {
                throw new CalculateException("Деление на ноль невозможно!");
            }
            return first / second;
        }
    }
}

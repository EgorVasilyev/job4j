package ru.job4j.solid.ocp;

        import ru.job4j.solid.srp.*;

        import java.util.HashMap;

public class EngineerCalculator extends Calculator {
    private HashMap<String, Action> engineerActions;

    public EngineerCalculator() {
        this.engineerActions = new HashMap<>(10);
        this.addActions();
    }

    private void addActions() {
        Sin sin =  new Sin();
        Cos cos = new Cos();
        Tg tg = new Tg();
        Ctg ctg = new Ctg();
        this.engineerActions.put(sin.getSymbol(), sin);
        this.engineerActions.put(cos.getSymbol(), cos);
        this.engineerActions.put(tg.getSymbol(), tg);
        this.engineerActions.put(ctg.getSymbol(), ctg);
        this.engineerActions.putAll(super.getActions());
    }

    public HashMap<String, Action> getActions() {
        return this.engineerActions;
    }

    public double getResult(double first, double second, String symbol) throws CalculateException {
        return this.engineerActions.get(symbol).calculate(first, second);
    }

    protected void showActions() {
        for (Action action : this.engineerActions.values()) {
            System.out.println(action.getSymbol() + "  " + action.description());
        }
    }

    private class Sin implements Action {

        @Override
        public String getSymbol() {
            return "sin";
        }

        @Override
        public String description() {
            return "Синус первого числа";
        }

        @Override
        public double calculate(double first, double second) {
            return Math.sin(first);
        }
    }

    private class Cos implements Action {

        @Override
        public String getSymbol() {
            return "cos";
        }

        @Override
        public String description() {
            return "Косинус первого числа";
        }

        @Override
        public double calculate(double first, double second) {
            return Math.cos(first);
        }
    }

    private class Tg implements Action {

        @Override
        public String getSymbol() {
            return "tg";
        }

        @Override
        public String description() {
            return " Тангенс первого числа";
        }

        @Override
        public double calculate(double first, double second) {
            return Math.tan(first);
        }
    }

    private class Ctg implements Action {

        @Override
        public String getSymbol() {
            return "ctg";
        }

        @Override
        public String description() {
            return "Котангенс первого числа";
        }

        @Override
        public double calculate(double first, double second) {
            return 1 / Math.tan(first);
        }
    }
}

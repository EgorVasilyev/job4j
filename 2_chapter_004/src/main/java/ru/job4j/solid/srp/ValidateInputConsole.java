package ru.job4j.solid.srp;

import java.util.Scanner;

public class ValidateInputConsole implements Input {
    private final Scanner console = new Scanner(System.in);

    public ValidateInputConsole() {
    }

    @Override
    public String next() {
        return this.console.nextLine();
    }

    @Override
    public String askString(String question) {
        System.out.println((question));
        return this.console.nextLine();
    }

    @Override
    public double askDouble(String value) throws NumberFormatException {
        return Double.parseDouble(value);
    }
}

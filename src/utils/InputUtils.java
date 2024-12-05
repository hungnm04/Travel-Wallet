package utils;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Function;

public class InputUtils {
    private final Scanner scanner;

    public InputUtils(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getStringInput(String prompt) {
        return getInput(prompt, Function.identity(), "Invalid input. Please try again.");
    }

    public double getDoubleInput(String prompt) {
        return getInput(prompt, Double::parseDouble, "Invalid input. Please enter a valid number.");
    }

    public int getIntInput(String prompt) {
        return getInput(prompt, Integer::parseInt, "Invalid input. Please enter a valid number.");
    }

    public LocalDate getDateInput(String prompt) {
        return getInput(prompt, LocalDate::parse, "Invalid input. Please enter a valid date in the format yyyy-MM-dd.");
    }

    private <T> T getInput(String prompt, Function<String, T> parser, String errorMessage) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("menu")) {
                throw new MenuException();
            }
            try {
                return parser.apply(input);
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }
}
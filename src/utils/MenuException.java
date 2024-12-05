package utils;

public class MenuException extends RuntimeException {
    public MenuException() {
        super("User requested to return to the main menu.");
    }
}
package application;

import controller.TravelWalletController;
import service.CurrencyConverter;
import service.SimpleCurrencyConverter;
import service.TravelWalletUseCase;
import service.TravelWalletUseCaseImpl;
import view.MenuView;

import java.util.Scanner;

public class TravelWalletApp {
    public static void main(String[] args) {
        CurrencyConverter currencyConverter = new SimpleCurrencyConverter();
        TravelWalletUseCase travelWalletUseCase = new TravelWalletUseCaseImpl(currencyConverter);
        TravelWalletController controller = new TravelWalletController(travelWalletUseCase);

        Scanner scanner = new Scanner(System.in);
        MenuMethods menuMethods = new MenuMethods(controller, scanner);
        MenuView menuView = new MenuView(scanner, menuMethods);

        menuView.run();
    }
}
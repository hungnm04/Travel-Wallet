package application;

import controller.TravelWalletController;
import java.util.Scanner;
import service.CurrencyConverter;
import service.SimpleCurrencyConverter;
import service.TravelWalletUseCase;
import service.TravelWalletUseCaseImpl;
import utils.InputUtils;
import view.MenuView;

public class TravelWalletApp {
    public static void main(String[] args) {
        CurrencyConverter currencyConverter = new SimpleCurrencyConverter();
        TravelWalletUseCase travelWalletUseCase = new TravelWalletUseCaseImpl(currencyConverter);
        TravelWalletController controller = new TravelWalletController(travelWalletUseCase);

        Scanner scanner = new Scanner(System.in);
        InputUtils inputUtils = new InputUtils(scanner);
        MenuMethods menuMethods = new MenuMethods(controller, inputUtils);
        MenuView menuView = new MenuView(menuMethods, inputUtils);

        menuView.run();
    }
}
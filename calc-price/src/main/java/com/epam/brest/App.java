package com.epam.brest;

import org.apache.log4j.Logger;

public class App {

    public static final String PRICE_DISTANCE = "src/main/resources/distance.price";
    public static final String PRICE_WEIGHT = "src/main/resources/weight.price";
    public static final String EXIT_KEY = "Q";
    private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        int menuDialogSwitcher = 0;
        Double[] enteredValues = new Double[2];
        String userDataFromConsoleInput = "";
        while (!isExitValue(userDataFromConsoleInput)) {
            printMenuDialog(menuDialogSwitcher);
            try {
                userDataFromConsoleInput = InputUtilities.readString();
                if (isExitValue(userDataFromConsoleInput)) {
                    logger.info(EXIT_KEY + " key was inputed by user. Exit from programm");
                    break;
                }
                enteredValues[menuDialogSwitcher] = InputUtilities.getPositiveDoubleFromString(userDataFromConsoleInput);
                menuDialogSwitcher++;
            } catch (Exception ex) {
                logger.info("Incorrect data. Positive Double or "+EXIT_KEY+" key available");
                break;
            }

            if (menuDialogSwitcher == 2) {

                calculateResult(enteredValues);
                menuDialogSwitcher = 0;
            }
        }
    }

    private static void printMenuDialog(int i) {
        if (i == 0) {
            System.out.println("Please enter distance km or " + EXIT_KEY + " for exit");
        } else if (i == 1) {
            System.out.println("Please enter weight kg or " + EXIT_KEY + " for exit");
        } else {
            logger.error("Abnormal situation. Max menu lvl is 2");
        }
    }

    private static boolean isExitValue(String value) {
        return value.equalsIgnoreCase(EXIT_KEY);
    }

    private static void calculateResult(Double[] enteredValues){
        Double distance = enteredValues[0];
        Double weight = enteredValues[1];
        Double priceDistance = InputUtilities.getPriceFromFile(distance, PRICE_DISTANCE);
        Double priceWeight = InputUtilities.getPriceFromFile(weight, PRICE_WEIGHT);
        Double result = distance * priceDistance + weight * priceWeight;
        logger.info("For distance: " + distance + " km. price: " + priceDistance + " per km.; weight: "
                + weight + " kg. price: " + priceWeight
                + " per kg. result summ: " + result);
    }
}

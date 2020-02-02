package com.epam.brest;

import org.apache.log4j.Logger;

public class App {

    public static final String PRICE_DISTANCE = "src/main/resources/distance.price";
    public static final String PRICE_WEIGHT = "src/main/resources/weight.price";
    private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {

        int menuDialogSwitcher = 0;
        Double[] enteredValues = new Double[2];

        while (true) {

            printMenuDialog(menuDialogSwitcher);
            try {
                String userDataFromConsoleInput = InputUtilities.readString();
                if (isExitValue(userDataFromConsoleInput)) {
                    logger.info("Q key was inputed by user. Exit from programm");
                    break;
                }
                enteredValues[menuDialogSwitcher] = InputUtilities.getPositiveDoubleFromString(userDataFromConsoleInput);
                menuDialogSwitcher++;
            } catch (Exception ex) {
                logger.info("Incorrect data. Positive Double or Q key available");
                continue;
            }

            if (menuDialogSwitcher == 2) {

                calculateResult(enteredValues);
                menuDialogSwitcher = 0;
            }

        }

    }

    private static void printMenuDialog(int i) {
        if (i == 0) {
            System.out.println("Please enter distance km or Q for exit");
        } else if (i == 1) {
            System.out.println("Please enter weight kg or Q for exit");
        } else {
            logger.error("Abnormal situation. Max menu lvl is 2");
        }
    }

    private static boolean isExitValue(String value) {
        return value.equalsIgnoreCase("Q");
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

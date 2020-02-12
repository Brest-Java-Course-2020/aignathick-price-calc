package com.epam.brest;

import org.apache.log4j.Logger;

public class Menu implements IMenu {
    public final String EXIT_KEY = "Q";
    private static Logger logger = Logger.getLogger(Menu.class);

    @Override
    public void printMenuDialog(int i) {
        if (i == 0) {
            System.out.println("Please enter distance km or " + EXIT_KEY + " for exit");
        } else if (i == 1) {
            System.out.println("Please enter weight kg or " + EXIT_KEY + " for exit");
        } else {
            logger.error("Abnormal situation. Max menu lvl is 2");
        }
    }

    @Override
    public boolean isExitValue(String value) {
        return value.equalsIgnoreCase(EXIT_KEY);
    }

    @Override
    public String getExitKey() {
        return EXIT_KEY;
    }
}

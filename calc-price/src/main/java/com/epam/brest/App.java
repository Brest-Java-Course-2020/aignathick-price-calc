package com.epam.brest;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-config.xml");
        IInputUtilit inputUtilit = (InputUtilit) applicationContext.getBean("inputUtilit");
        ICalculator calculator = (Calculator) applicationContext.getBean("calculator");
        IMenu menu = (Menu) applicationContext.getBean("menu");

        int menuDialogSwitcher = 0;
        Double[] enteredValues = new Double[2];
        String userDataFromConsoleInput = "";

        while (!menu.isExitValue(userDataFromConsoleInput)) {
            menu.printMenuDialog(menuDialogSwitcher);
            try {
                userDataFromConsoleInput = inputUtilit.readString();
                if (menu.isExitValue(userDataFromConsoleInput)) {
                    logger.info(menu.getExitKey() + " key was inputed by user. Exit from programm");
                    break;
                }
                enteredValues[menuDialogSwitcher] = inputUtilit.getPositiveDoubleFromString(userDataFromConsoleInput);
                menuDialogSwitcher++;
            } catch (Exception ex) {
                logger.info("Incorrect data. Positive Double or " + menu.getExitKey() + " key available");
                break;
            }

            if (menuDialogSwitcher == 2) {
                calculator.calculateResult(enteredValues);
                menuDialogSwitcher = 0;
            }
        }
    }
}

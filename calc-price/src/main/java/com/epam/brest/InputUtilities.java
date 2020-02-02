package com.epam.brest;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

public class InputUtilities {

    private static Logger logger = Logger.getLogger(InputUtilities.class);
    public static final String DELIMITER_VALUE = ";";

    static class InputUtilException extends Exception {
        public InputUtilException(String msg) {
            super(msg);
        }
    }

    public static String readString() throws InputUtilException {

        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextLine();
        } catch (Exception ex) {
            logger.error("Read String from keyboart error: " + ex);
            throw new InputUtilException("Read String from keyboart exception");
        }

    }

    public static Double getDoubleFromString(String value) throws InputUtilException {

        try {
            return Double.valueOf(value);
        } catch (Exception ex) {
            logger.error("Convert String " + value + " to Double error:" + ex);
            throw new InputUtilException("Convert String to Double exception");
        }

    }

    public static Double getPositiveDoubleFromString(String value) throws InputUtilException {
        Double positiveValue;
        try {
            positiveValue = Double.valueOf(value);


        } catch (Exception ex) {
            logger.error("Convert String " + value + " to positive Double error:" + ex);
            throw new InputUtilException("Convert String to positive Double exception");
        }

        if (positiveValue >= 0) {
            return Double.valueOf(value);
        } else {
            logger.error("Convert String " + value + " to positive Double error:");
            throw new InputUtilException("Convert String to positive Double exception");
        }

    }

    public static Double getPriceFromFile(Double userInputedValue, String priceFile) {
        Double priceFromFile = 0d;
        try {

            File file = new File(priceFile);
            Scanner sc = new Scanner(file);
            Map<Double, Double> priceMap = new TreeMap<>();
            Set<Double> priceSet = new TreeSet<>();

            while (sc.hasNextLine()) {
                String priceLine = sc.nextLine();
                int delimiter = priceLine.indexOf(DELIMITER_VALUE);
                String priceKey = priceLine.substring(0, delimiter);
                String priceValue = priceLine.substring(++delimiter);
                priceMap.put(InputUtilities.getPositiveDoubleFromString(priceKey), InputUtilities.getPositiveDoubleFromString(priceValue));
                priceSet.add(InputUtilities.getPositiveDoubleFromString(priceKey));
            }

            for (Double setValue :
                    priceSet) {
                if (setValue <= userInputedValue) {
                    priceFromFile = priceMap.get(setValue);
                }
            }

            logger.info("for entred value " + userInputedValue + " price is " + priceFromFile);
            return priceFromFile;

        } catch (Exception ex) {
            logger.error("Cant get price from price file: " + ex);
            return 0d;
        }
    }

}

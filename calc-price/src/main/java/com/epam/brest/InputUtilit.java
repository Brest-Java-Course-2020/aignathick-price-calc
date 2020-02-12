package com.epam.brest;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

public class InputUtilit implements IInputUtilit {

    private static Logger logger = Logger.getLogger(InputUtilit.class);
    public final String DELIMITER_VALUE = ";";

    class InputUtilException extends Exception {
        public InputUtilException(String msg) {
            super(msg);
        }
    }

    @Override
    public String readString() throws InputUtilException {

        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } catch (Exception ex) {
            logger.error("Read String from keyboart error: " + ex);
            throw new InputUtilException("Read String from keyboart exception");
        }

    }

    @Override
    public Double getDoubleFromString(String value) throws InputUtilException {

        try {
            return Double.valueOf(value);
        } catch (Exception ex) {
            logger.error("Convert String " + value + " to Double error:" + ex);
            throw new InputUtilException("Convert String to Double exception");
        }

    }

    @Override
    public Double getPositiveDoubleFromString(String value) throws InputUtilException {
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

    @Override
    public Double getPriceFromFile(Double userInputedValue, String priceFile) {
        Double priceFromFile = 0d;
        File file = new File(priceFile);
        try (Scanner sc = new Scanner(file)) {

            Map<Double, Double> priceMap = new TreeMap<>();
            Set<Double> priceSet = new TreeSet<>();

            while (sc.hasNextLine()) {
                String priceLine = sc.nextLine();
                int delimiter = priceLine.indexOf(DELIMITER_VALUE);
                String priceKey = priceLine.substring(0, delimiter);
                String priceValue = priceLine.substring(++delimiter);
                priceMap.put(getPositiveDoubleFromString(priceKey), getPositiveDoubleFromString(priceValue));
                priceSet.add(getPositiveDoubleFromString(priceKey));
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

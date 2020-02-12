package com.epam.brest;

import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class Calculator implements ICalculator {

    private static Logger logger = Logger.getLogger(Calculator.class);
    private IInputUtilit inputUtilities;
    public final String PRICE_DISTANCE = "src/main/resources/distance.price";
    public final String PRICE_WEIGHT = "src/main/resources/weight.price";

    public Calculator() {
        init();
    }

    public void init() {
        this.inputUtilities = new InputUtilit();
    }

    @Override
    public void calculateResult(Double[] enteredValues) {
        BigDecimal distance = new BigDecimal(enteredValues[0]);
        BigDecimal weight = new BigDecimal(enteredValues[1]);
        BigDecimal priceDistance = new BigDecimal(this.inputUtilities.getPriceFromFile(enteredValues[0], PRICE_DISTANCE));
        BigDecimal priceWeight = new BigDecimal(this.inputUtilities.getPriceFromFile(enteredValues[1], PRICE_WEIGHT));
        BigDecimal result = distance.multiply(priceDistance).add(weight.multiply(priceWeight));
        logger.info("For distance: " + distance + " km. price: " + priceDistance + " per km.; weight: "
                + weight + " kg. price: " + priceWeight
                + " per kg. result summ: " + result);
    }
}

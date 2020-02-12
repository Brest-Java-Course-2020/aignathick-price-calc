package com.epam.brest;

public interface IInputUtilit {
    String readString() throws InputUtilit.InputUtilException;

    Double getDoubleFromString(String value) throws InputUtilit.InputUtilException;

    Double getPositiveDoubleFromString(String value) throws InputUtilit.InputUtilException;

    Double getPriceFromFile(Double userInputedValue, String priceFile);
}

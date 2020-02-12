package com.epam.brest;

public interface IMenu {
    void printMenuDialog(int i);

    boolean isExitValue(String value);

    String getExitKey();
}

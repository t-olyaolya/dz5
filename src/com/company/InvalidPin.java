package com.company;

/**
 * Created by tyuly on 29.11.2016.
 */
public class InvalidPin extends Exception {
    InvalidPin () {
        super();
        System.out.println("Неверный pin - код. Проверьте правильность ввода");
    }
}

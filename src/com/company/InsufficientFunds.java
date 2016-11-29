package com.company;

/**
 * Created by tyuly on 29.11.2016.
 */
public class InsufficientFunds extends Exception {
    InsufficientFunds () {
        super();
        System.out.println("Недостаточно средств на счете. Необходимо пополнить счет");
    }
}

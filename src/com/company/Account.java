package com.company;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by tyuly on 29.11.2016.
 */
public class Account extends DecoratorAccount implements Serializable {
    static int file = 0;

    Account(Client client, String number, String pin) {
        setClient(client);
        setNumber(number);
        setPin(pin);
        file ++;
    }
}

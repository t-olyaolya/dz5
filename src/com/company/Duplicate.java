package com.company;

/**
 * Created by tyuly on 29.11.2016.
 */
public class Duplicate extends Exception {
    Duplicate (Object o) {
        super();
        if (o instanceof Account) {
           System.out.println("Такой номер карты уже имеется. Следует ввести другой номер");
        }
        if (o instanceof Client) {
            System.out.println("Такой клиент уже имеется. Введите другие имя и фамилию");
        }

    }
}

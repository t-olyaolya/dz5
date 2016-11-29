package com.company;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by tyuly on 29.11.2016.
 */
public class Card implements Serializable {
    private String number;
    private String pin;
    private Client client;
    private double count;
    static int file = 0;
    public  long version = 100;

    Card(Client client, String number, String pin) {
        this.client = client;
        this.number = number;
        this.pin = pin;
        file ++;

    }

    public void setCount(double count) {
        this.count += count;
    }

    public double getCount() {
        return count;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}

package com.company;

/**
 * Created by tyuly on 28.12.2016.
 */
public class DecoratorAccount implements AccountInterface {
    public double count;
    public String number;
    public String pin;
    public Client client;
    static int file = 0;

    @Override
    public double getCount() {
        synchronized (this) {
            return count;
        }
    }

    @Override
    public void setCount(double count) {
        synchronized (this) {
            this.count += count;
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public synchronized String getNumber() {
            return number;
    }

    public synchronized void setNumber(String number) {
        this.number = number;
    }

    public synchronized String getPin() {
        return pin;
    }

    public synchronized void setPin(String pin) {
        this.pin = pin;
    }
}

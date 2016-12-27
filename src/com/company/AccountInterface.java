package com.company;

/**
 * Created by tyuly on 28.12.2016.
 */
public interface AccountInterface {
    void setCount(double count);

    double getCount();

    Client getClient();

    void setClient(Client client);

    String getNumber();

    void setNumber(String number);

    String getPin();

    void setPin(String pin);
}


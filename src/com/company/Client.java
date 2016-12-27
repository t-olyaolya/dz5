package com.company;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tyuly on 29.11.2016.
 */
public class Client implements Serializable {
    static int file = 0;
    private String name;
    private String surname;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    public long version = 100;


    Client (String surname, String name) {
        this.surname = surname;
        this.name = name;
        file ++;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAccounts(Account account) {
        accounts.add(account);
    }

}

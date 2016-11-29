package com.company;

/**
 * Created by tyuly on 29.11.2016.
 */
public class AccountLock extends Exception {
    AccountLock () {
        super();
        System.out.println("Ваш аккаунт заблокирован. Превышен допустимый лимит ввода неверного pin - кода. Повторите попытку позже");
    }

}

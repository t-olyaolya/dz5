package com.company;

import java.io.*;
import java.util.Scanner;


public class Main {
    static Scanner in = new Scanner(System.in);

    public static void menu () {
        Bank bank = new Bank();
        System.out.println("Выберите действие:" + "\n" + "1 - добавить пользователя" + "\n" + "2 - удалить пользователя"
        + "\n" + "3 - добавить карту" + "\n" + "4 - удалить карту" + "\n" + "5 - проверить состояние счета"
        + "\n" + "6 - пополнить счет" + "\n" + "7 - снять деньги" + "\n" + "8 - список клиентов"
        + "\n" + "9 - список карт");
        int c = in.nextInt();
        switch (c) {
            case 1:
                bank.createClient();
                break;
            case 2:
                bank.deleteClient();
                break;
            case 3:
                bank.createCard();
                break;
            case 4:
                bank.deleteCard();
                break;
            case 5:
                System.out.println(bank.checkCount());
                break;
            case 6:
                bank.put();
                break;
            case 7:
                bank.pull();
                break;
            case 8:
              bank.getClients();
              break;
            case 9:
                bank.getCards();
                break;
            default:
                menu();
        }
        menu();


    }


    public static void main(String[] args) {
       menu();




    }
}

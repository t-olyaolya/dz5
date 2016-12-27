package com.company;

import java.util.Scanner;


public class Main {
    static Scanner in = new Scanner(System.in);
    static Bank bank = new Bank();
    static final int K = 5;

    public static void menu () {
        System.out.println("Выберите действие:" + "\n" + "1 - добавить пользователя" + "\n" + "2 - удалить пользователя"
        + "\n" + "3 - добавить карту" + "\n" + "4 - удалить карту" + "\n" + "5 - проверить состояние счета"
        + "\n" + "6 - пополнить счет" + "\n" + "7 - снять деньги" + "\n" + "8 - список клиентов"
        + "\n" + "9 - список карт"  + "\n" + "10-задание 1" + "\n" + "11-задание 2");
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
            case 10:
                Account account = bank.getCard();
                bank.checkCardPin(0, account);
                try {
                    Decreaser decreaser = new Decreaser(account);
                    Increaser increaser = new Increaser(account);
                    //decreaser.setPriority(Thread.MAX_PRIORITY);
                    //increaser.setPriority(Thread.NORM_PRIORITY);
                    decreaser.start();
                    increaser.start();
                    decreaser.join();
                    increaser.join();
                }
                catch (InterruptedException e) {
                    throw  new RuntimeException(e);
                }
                 break;
            case 11:
                Account bankAccount = bank.getCard();
                bank.checkCardPin(0, bankAccount);
                IncreaseDecrease inc = new IncreaseDecrease(bankAccount);
                SequentialIncreaser si = new SequentialIncreaser(inc);
                SequentialDecreaser sd = new SequentialDecreaser(inc);
                try {
                    Thread thread = new Thread(si);
                    Thread thread1 = new Thread(sd);
                    thread.start();
                    thread1.start();
                    thread.join();
                    thread1.join();
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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

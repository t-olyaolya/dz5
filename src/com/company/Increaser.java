package com.company;

import static com.company.Main.bank;

/**
 * Created by tyuly on 26.12.2016.
 */
public class Increaser extends Thread {
    private Account account;

    public Increaser (Account c) {
        account = c;
    }

    @Override
    public void run () {
        for (int i = 0; i < Main.K; i++) {
            synchronized (bank) {
                bank.pullThread(account);
            }
            //this.interrupt();
            try {
               Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                throw  new RuntimeException(e);
            }
        }
    }
}

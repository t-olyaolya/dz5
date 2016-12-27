package com.company;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tyuly on 28.12.2016.
 */
public class IncreaseDecrease {
    private ReentrantLock locker;
    private Condition condition;
    private Account account;
    private int k = 0;

    IncreaseDecrease(Account account) {
        this.account = account;
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public void pull () {
        try {
            locker.lock();
            while (k != 0) {
                condition.await();
            }
            Main.bank.pullThread(account);
            k++;
            condition.signalAll();
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
            }
        finally {
            locker.unlock();
        }
    }

        public void put() {
            try {
                locker.lock();
                while (k == 0) {
                    condition.await();
                }
                Main.bank.putThread(account);
                System.out.println(" ");
                k = 0;
                condition.signalAll();
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            finally {
                locker.unlock();
            }
        }


}

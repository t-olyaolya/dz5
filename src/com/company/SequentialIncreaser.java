package com.company;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by tyuly on 27.12.2016.
 */
public class SequentialIncreaser implements Runnable {
    private IncreaseDecrease increaseDecrease;

    public SequentialIncreaser(IncreaseDecrease increaseDecrease) {
        this.increaseDecrease = increaseDecrease;
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.K; i++) {
            increaseDecrease.pull();

        }

    }
}

package com.able.studyproject.lock.lock.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @param
 * @author jipeng
 * @date 2020-05-07 18:23
 */
public class AtomicArrayDemo {
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(20);

    public static void main(String[] args) throws InterruptedException {
        Decrement decrement = new Decrement(atomicIntegerArray);
        Increment increment = new Increment(atomicIntegerArray);

        Thread[] threadsIncrement = new Thread[100];
        Thread[] threadsDecrement = new Thread[100];
        for (int i = 0; i < threadsDecrement.length; i++) {
            threadsDecrement[i] = new Thread(decrement);

        }
        for (int i = 0; i < threadsIncrement.length; i++) {
            threadsIncrement[i] = new Thread(increment);
        }

        for (int i = 0; i < 100; i++) {
            threadsDecrement[i].start();
            threadsDecrement[i].join();

            threadsIncrement[i].start();
            threadsIncrement[i].join();
        }
        System.out.println("atomicIntegerArray = " + atomicIntegerArray);

    }

}

class Decrement implements Runnable {

    private AtomicIntegerArray atomicIntegerArray;

    public Decrement(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {

            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                atomicIntegerArray.getAndDecrement(i);
            }
        }
    }
}

class Increment implements Runnable {

    private AtomicIntegerArray atomicIntegerArray;

    public Increment(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {

            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                atomicIntegerArray.getAndIncrement(i);
            }
        }
    }
}


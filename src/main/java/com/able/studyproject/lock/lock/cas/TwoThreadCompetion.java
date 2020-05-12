package com.able.studyproject.lock.lock.cas;

import sun.awt.SunHints;

/**
 * @param
 * @author jipeng
 * @date 2020-05-08 14:16
 */
public class TwoThreadCompetion{
    volatile String value="0";
    public static void main(String[] args) throws InterruptedException {
      TwoThreadCompetion task=new TwoThreadCompetion();
        Thread aa = new Thread(() -> {
            task.compareAndSwap("0", Thread.currentThread().getName());
        }, "AA");
        Thread bb = new Thread(() -> {
            task.compareAndSwap("0", Thread.currentThread().getName());
        }, "BB");
       aa.start();
       aa.join();
       bb.start();
       bb.join();
        System.out.println("task.value = " + task.value);
    }
    public  synchronized void compareAndSwap(String exceptValue,String newValue){
        if (exceptValue.equals(value)) {
            value=newValue;
        }
    }

}


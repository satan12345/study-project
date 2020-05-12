package com.able.studyproject.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @param
 * @author jipeng
 * @date 2020-05-09 23:44
 */
public class ArrayBlockingQueueDemo {

    public ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(3);

    public static void main(String[] args) {

        ArrayBlockingQueueDemo arrayBlockingQueueDemo=new ArrayBlockingQueueDemo();
         new Thread(new Revierew(arrayBlockingQueueDemo.arrayBlockingQueue),"AA").start();
         new Thread(new Consumer(arrayBlockingQueueDemo.arrayBlockingQueue),"BB").start();
    }
}

class Revierew implements Runnable {

    ArrayBlockingQueue<String> arrayBlockingQueue;

    public Revierew(ArrayBlockingQueue<String> arrayBlockingQueue) {

        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    @Override
    public void run() {
        System.out.println("10个候选人都来啦");
        try {
            for (int i = 0; i < 10; i++) {
                arrayBlockingQueue.put("候选人" + i);
                System.out.println("安排好了候选人" + i);
            }
            arrayBlockingQueue.put("STOP");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Consumer implements Runnable {
    ArrayBlockingQueue<String> arrayBlockingQueue;

    public Consumer(ArrayBlockingQueue<String> arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    @Override
    public void run() {
        String msg;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            while (!(msg = arrayBlockingQueue.take()).equals("STOP")) {
                System.err.println(msg + "到了");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("所有候选人结束");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


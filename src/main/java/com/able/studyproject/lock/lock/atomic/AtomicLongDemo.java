package com.able.studyproject.lock.lock.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @param
 * @author jipeng
 * @date 2020-05-08 11:03
 */
public class AtomicLongDemo {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        AtomicLong atomicLong=new AtomicLong();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new MyTask1(atomicLong));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){

        }
        long end = System.currentTimeMillis();
        System.out.println("消耗时长为: "+(end-start));

    }
    static class MyTask1 implements Runnable{

        AtomicLong atomicLong;

        public MyTask1(AtomicLong atomicLong) {
            this.atomicLong = atomicLong;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                atomicLong.incrementAndGet();
            }
        }
    }

}


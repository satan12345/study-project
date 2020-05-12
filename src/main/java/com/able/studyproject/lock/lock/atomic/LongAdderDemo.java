package com.able.studyproject.lock.lock.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @param
 * @author jipeng
 * @date 2020-05-08 11:03
 */
public class LongAdderDemo {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        LongAdder longAdder=new LongAdder();
        longAdder.increment();
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            executorService.submit(new MyTask(longAdder));
//        }
//        executorService.shutdown();
//        while (!executorService.isTerminated()){
//
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("消耗时长为: "+(end-start));

    }
}
class MyTask implements Runnable{

   LongAdder longAdder;

    public MyTask(LongAdder longAdder) {
        this.longAdder = longAdder;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            longAdder.increment();
        }
    }
}


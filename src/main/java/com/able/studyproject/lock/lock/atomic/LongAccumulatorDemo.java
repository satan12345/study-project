package com.able.studyproject.lock.lock.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @param
 * @author jipeng
 * @date 2020-05-08 12:10
 */
public class LongAccumulatorDemo {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        LongAccumulator longAccumulator=new LongAccumulator((x,y)->x+y,0);
//        for (int i = 0; i < 10; i++) {
//            longAccumulator.accumulate(i);
//            System.out.println("longAccumulator.get() = " + longAccumulator.get());
//        }

        IntStream.range(0,100).forEach(x-> executorService.submit(()-> longAccumulator.accumulate(x)));
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        System.out.println("longAccumulator.get() = " + longAccumulator.get());
    }
}


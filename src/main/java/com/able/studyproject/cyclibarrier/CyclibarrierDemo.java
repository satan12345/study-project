package com.able.studyproject.cyclibarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @param
 * @author jipeng
 * @date 2020-05-13 11:08
 */
public class CyclibarrierDemo {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5,
                ()-> System.out.println(Thread.currentThread().getName()+"所有人都到场了，大家统一出发"));
        for (int i = 0; i < 10; i++) {
            new Thread(new MyTask(i,cyclicBarrier)).start();
        }
    }

   static class MyTask implements Runnable{

        int id;
        CyclicBarrier cyclicBarrier;

        public MyTask(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+id+"现在前往集合地点");
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程"+id+"到了集合地点,开始等待其他人到达");
//           try {
//               TimeUnit.SECONDS.sleep(1);
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
            try {
                cyclicBarrier.await();
                System.err.println("线程"+id+"出发了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}


package com.able.studyproject.lock.lock.atomic;

import org.springframework.beans.BeanUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @param
 * @author jipeng
 * @date 2020-05-07 20:58
 */
public class AtomicIntegerFiledUpdaterDemo implements Runnable {

    int score;
    int num;
//    private  AtomicIntegerFieldUpdater<Candidate> scoreUpdater=
//         AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score")   ;

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerFiledUpdaterDemo task = new AtomicIntegerFiledUpdaterDemo();

        for (int i = 0; i < 100; i++) {

            Thread aa = new Thread(task, "AA"+i);
            aa.start();
           // aa.join();
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println("task.tom.score = " + task.score);
//        System.out.println("task.piter.score = " + task.piter.score);
    }


    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            score++;
//            scoreUpdater.getAndIncrement(piter);
        }

    }
    public static   class Candidate{
      volatile    int score;


    }
}




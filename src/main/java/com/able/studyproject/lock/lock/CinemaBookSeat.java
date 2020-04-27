package com.able.studyproject.lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 延时多线程预定电影院座位
 *
 * @param
 * @author jipeng
 * @date 2020-04-27 14:55
 */
public class CinemaBookSeat {

    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        CinemaBookSeat cinemaBookSeat = new CinemaBookSeat();
//        new Thread(() -> cinemaBookSeat.bookSeat(), "AA").start();
//        new Thread(() -> cinemaBookSeat.bookSeat(), "BB").start();
//        new Thread(() -> cinemaBookSeat.bookSeat(), "CC").start();
//        new Thread(() -> cinemaBookSeat.bookSeat(), "DD").start();

        new Thread(() -> {
            while (true) {

                cinemaBookSeat.output("旗木卡卡西");
            }

        }, "AA").start();
        new Thread(() -> {
            while (true) {

                cinemaBookSeat.output("宇智波鼬");
            }


        }, "BB").start();
    }


    public void output(String s) {
        int length = s.length();

        try {
            //lock.lock();
            // System.err.println(Thread.currentThread().getName()+"开始打印");
            for (int i = 0; i < length; i++) {
                System.out.print(s.charAt(i));
            }
            System.out.println(" ");
            //  System.err.println(Thread.currentThread().getName()+"结束打印");
        } finally {
            //  lock.unlock();
        }
    }

    public void bookSeat() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "开始预定座位");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "完成预定座位");
        } finally {
            lock.unlock();
        }

    }


}


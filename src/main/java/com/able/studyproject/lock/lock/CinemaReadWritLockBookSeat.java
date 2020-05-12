package com.able.studyproject.lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @param
 * @author jipeng
 * @date 2020-04-29 17:16
 */
public class CinemaReadWritLockBookSeat {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) throws InterruptedException {
         new Thread(() -> {
                CinemaReadWritLockBookSeat.read();
         },"读锁1").start();
        TimeUnit.SECONDS.sleep(1);
//         new Thread(() -> {
//                CinemaReadWritLockBookSeat.read();
//         },"读锁2").start();
//         new Thread(() -> {
//                CinemaReadWritLockBookSeat.read();
//         },"读锁3").start();


         new Thread(() -> {
                CinemaReadWritLockBookSeat.write();
         },"写锁1").start();
         TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            CinemaReadWritLockBookSeat.read();
        }, "读锁2").start();
//         new Thread(() -> {
//                CinemaReadWritLockBookSeat.write();
//         },"写锁2").start();
    }
    private static void read() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "得到了读锁,正在读取");
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"得到了写锁,正在写入");
            TimeUnit.SECONDS.sleep(5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放了写锁");
            writeLock.unlock();
        }
    }
}


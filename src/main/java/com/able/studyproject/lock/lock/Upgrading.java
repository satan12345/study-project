package com.able.studyproject.lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁的升级降级
 *
 * @param
 * @author jipeng
 * @date 2020-05-07 14:45
 */
public class Upgrading {
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void readUpgrading() {
        try {
           readLock.lock();
            System.out.println(Thread.currentThread().getName()+"得到了读锁，正在读取");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"尝试获取写锁");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"获取写锁成功,升级成功");
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放读锁");
            readLock.unlock();
        }
    }

    public static void writeDowngrading() {
        try {
            System.out.println(Thread.currentThread().getName() + "尝试获取写锁");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取写锁成功");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"尝试获取读锁");
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"在不释放写锁的情况下获取读锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放读锁");
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("先演示降级");
        Thread t1 = new Thread(() -> {
            writeDowngrading();
        }, "线程1");
        t1.start();
        t1.join();

        System.out.println("升级是不行的");

         new Thread(() -> {
                   readUpgrading();
         },"线程2===").start();
    }
}


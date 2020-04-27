package com.able.studyproject.lock.lock;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @param
 * @author jipeng
 * @date 2020-04-27 10:47
 */
public class LockInterruptibly implements Runnable {


    Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        LockInterruptibly lockInterruptibly=new LockInterruptibly();
         Thread AA=new Thread(lockInterruptibly,"AA");
         Thread BB=new Thread(lockInterruptibly,"BB");
         AA.start();
         TimeUnit.MILLISECONDS.sleep(300);
         BB.start();
         TimeUnit.SECONDS.sleep(1);
         AA.interrupt();
    }

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName()+"获取到了锁");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+"睡眠期间被中断");
            }
            finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放了锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "获取锁期间被中断了");
            //e.printStackTrace();
        }

    }
}


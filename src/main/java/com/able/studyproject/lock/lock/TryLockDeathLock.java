package com.able.studyproject.lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用 trylock来避免死锁
 *
 * @param
 * @author jipeng
 * @date 2020-04-26 18:59
 */
public class TryLockDeathLock implements Runnable {


    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
      TryLockDeathLock tryLockDeathLock1=new TryLockDeathLock();


       new Thread(tryLockDeathLock1,"AA").start();

       new Thread(tryLockDeathLock1,"BB").start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {

                String threadName = Thread.currentThread().getName();
                if (threadName.equals("AA")) {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(threadName + "获取到了锁1");
                            TimeUnit.SECONDS.sleep(2);
                            if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                    System.out.println(threadName + "成功获取到了2把锁");
                                    break;
                                } finally {
                                    lock2.unlock();
                                }
                            } else {
                                System.out.println(threadName + "获取锁2 失败");
                            }
                        } finally {
                            lock1.unlock();
                            System.out.println(threadName+"释放了锁1,继续循环");
                            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
                        }
                    } else {
                        System.out.println(threadName + "获取锁1 失败");

                    }
                }else {
                    if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(threadName + "获取到了锁2");
                            TimeUnit.SECONDS.sleep(2);
                            if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                    System.out.println(threadName + "成功获取到了2把锁");
                                    break;
                                } finally {
                                    lock1.unlock();
                                }
                            } else {
                                System.out.println(threadName + "获取锁1 失败");
                            }
                        } finally {
                            lock2.unlock();
                            System.out.println(threadName+"释放了锁2,继续循环");
                            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
                        }
                    } else {
                        System.out.println(threadName + "获取锁2 失败");

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


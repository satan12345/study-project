package com.able.studyproject.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock不会像synchronized一样 异常的时候自动释放锁
 * 所以最佳实践是 finally 中释放锁 以便保证发生异常的时候 锁一定被释放
 *
 * @param
 * @author jipeng
 * @date 2020-04-24 18:54
 */
public class MustUnLock {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
         try {
              lock.lock();
             System.out.println(Thread.currentThread().getName());
         } finally {
             lock.unlock();
         }

    }
}


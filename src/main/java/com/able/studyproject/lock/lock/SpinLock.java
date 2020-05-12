package com.able.studyproject.lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @param
 * @author jipeng
 * @date 2020-05-07 15:46
 */
public class SpinLock {

    private AtomicReference<Thread> sign=new AtomicReference<>();
    public void lock(){
        Thread thread = Thread.currentThread();
        while (!sign.compareAndSet(null, thread)) {
            //没有设置成功 需要自旋等待
        }
    }
    public void unlock(){
        Thread thread = Thread.currentThread();

        sign.compareAndSet(thread,null);
    }
    public static void main(String[] args){

      SpinLock spinLock=new SpinLock();
        for (int i = 0; i < 10; i++) {
             new Thread(() -> {
                   try {
                       spinLock.lock();
                       System.out.println(Thread.currentThread().getName()+"获取到了自旋锁");
                       try {
                           TimeUnit.SECONDS.sleep(1);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }finally {
                       System.out.println(Thread.currentThread().getName()+"释放了自旋锁");
                         spinLock.unlock();
                   }

             },"线程"+i+"----").start();
        }
    }
}


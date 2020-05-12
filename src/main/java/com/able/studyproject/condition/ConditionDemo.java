package com.able.studyproject.condition;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @param
 * @author jipeng
 * @date 2020-05-12 18:41
 */
public class ConditionDemo {

    static Lock lock=new ReentrantLock();
    static Condition condition=lock.newCondition();
    public static void main(String[] args) throws InterruptedException {
         new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1
                    );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               ConditionDemo.method2();
         },"AA").start();
         method1();
    }

    public static void method1() throws InterruptedException {
         try {
              lock.lock();
             System.out.println(Thread.currentThread().getName()+"进入锁,条件不满足 开始等待");
             condition.await();

             System.out.println(Thread.currentThread().getName()+"进入锁，条件满足了 开始执后续任务");

         } finally {
             lock.unlock();
         }
    }
    public static void method2(){
         try {
              lock.lock();

             System.out.println(Thread.currentThread().getName()+"进入锁,准备工作完成，唤醒其他的线程");
             condition.signalAll();
         } finally {
             lock.unlock();
         }
    }
}


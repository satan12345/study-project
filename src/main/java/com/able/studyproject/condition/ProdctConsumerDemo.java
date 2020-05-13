package com.able.studyproject.condition;

import org.springframework.util.NumberUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @param
 * @author jipeng
 * @date 2020-05-12 18:50
 */
public class ProdctConsumerDemo {
    static private int numCount = 0;
    static private int MAX_COUNT=10;
    static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(MAX_COUNT);
    static Lock lock = new ReentrantLock();
    /**
     * 消费者线程的condition 为空 await  有元素的时候要唤醒 signalAll
     */
    static Condition consumerCondition = lock.newCondition();
    /**
     * 生产者线程的condition 满时 await 有空位是要唤醒 signalAll
     */
    static Condition productCondition = lock.newCondition();

    public static void main(String[] args) throws Exception {
        Consumer consumer = new Consumer();
        Producter producter = new Producter();

        new Thread(consumer, "消费者1").start();
        new Thread(consumer, "消费者2").start();
        new Thread(consumer, "消费者3").start();
        new Thread(consumer, "消费者4").start();

        new Thread(producter, "生产者1").start();
        new Thread(producter, "生产者2").start();
        new Thread(producter, "生产者3").start();
        new Thread(producter, "生产者4").start();
    }

    static class Consumer implements Runnable {


        @Override
        public void run() {
            try {

                consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void consumer() throws Exception {
            while (true) {
                try {
                    lock.lock();
                    while (numCount == 0) {
                        /**
                         * 此时为空,消费者线程暂停
                         */
                        System.err.println(Thread.currentThread().getName()+"队列满了,线程暂停");
                        consumerCondition.await();
                    }
                    numCount--;
                    String take = blockingQueue.take();
                    System.err.println(Thread.currentThread().getName() + "消费的数据为：" + take+" 队列中元素的数量为="+ numCount);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    productCondition.signal();
                } finally {
                    lock.unlock();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producter implements Runnable {


        @Override
        public void run() {
            try {
                product();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void product() throws InterruptedException {

            while (true) {
                try {
                    lock.lock();
                    while (numCount == MAX_COUNT) {
                        System.out.println(Thread.currentThread().getName()+"队列满了,线程暂停");
                        productCondition.await();
                    }
                    numCount++;
                    int i = ThreadLocalRandom.current().nextInt();
                    blockingQueue.put(i + "");
                    System.out.println(Thread.currentThread().getName() + "生产了数据：" +i+ " 队列中元素的数量为="+ numCount);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    consumerCondition.signal();
                } finally {
                    lock.unlock();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


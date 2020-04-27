package com.able.studyproject.lock.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @param
 * @author jipeng
 * @date 2020-04-27 15:30
 */
public class GetHoldCount {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

            try {
                for (int i = 0; i < 3; i++) {
                    lock.lock();
                }
                System.out.println("lock.getHoldCount() = " + lock.getHoldCount());

            } finally {
                for (int i = 0; i < 3; i++) {

                    lock.unlock();
                }
            }

    }
}


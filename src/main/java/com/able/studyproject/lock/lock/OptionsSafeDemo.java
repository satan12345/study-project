package com.able.studyproject.lock.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @param
 * @author jipeng
 * @date 2020-05-09 17:05
 */

public class OptionsSafeDemo implements Runnable {
    public static final String KEY = "小米";
    public ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap();

    public static void main(String[] args) throws InterruptedException {

        OptionsSafeDemo optionsSafeDemo = new OptionsSafeDemo();
        optionsSafeDemo.scores.put(KEY, 0);
        Thread aa = new Thread(optionsSafeDemo, "AA");
        Thread bb = new Thread(optionsSafeDemo, "BB");
        aa.start();
        bb.start();

        aa.join();
        bb.join();

        System.out.println(optionsSafeDemo.scores.get(KEY));
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            while (true) {
                Integer score = scores.get(KEY);
                Integer newScore = score + 1;
                if (scores.replace(KEY, score, newScore)) {
                    break;
                }
            }

        }

    }
}


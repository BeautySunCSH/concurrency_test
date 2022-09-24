package com.ma.concurrency.thread.unsafe;

import java.util.concurrent.CountDownLatch;

/**
 * 产生线程不安全问题的原因： num++ 不是原子性操作，被拆分成好几个步骤，在多线程并发执行的情况下，
 * 因为cpu调度，多线程快速切换，有可能两个同一时刻都读取了同一个num值，之后对它进行+1操作，导致线程安全性问题。
 */
public class UnsafeThread {
    private static int num = 0;
    private static CountDownLatch countDownLatch = new CountDownLatch(10);//创建10的计数器

    public static  void increate() {
        num++;
    }

    public static void main(String[] args) {
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    increate();
                }
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();//计数器每次-1
            }).start();
        }

        while (true) {
            if (countDownLatch.getCount() == 0) {
                System.out.println("num = " + num);
                break;
            }
        }
    }
}

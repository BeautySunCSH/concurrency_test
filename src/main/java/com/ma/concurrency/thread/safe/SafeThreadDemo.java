package com.ma.concurrency.thread.safe;

import java.util.concurrent.CountDownLatch;

public class SafeThreadDemo {

    private static int num = 0;
    private static CountDownLatch countDownLatch = new CountDownLatch(10);//创建10的计数器

    public static synchronized void increate() {
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

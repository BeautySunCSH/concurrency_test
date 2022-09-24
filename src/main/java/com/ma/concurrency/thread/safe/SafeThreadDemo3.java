package com.ma.concurrency.thread.safe;

public class SafeThreadDemo3 {

    /**
     * synchronized修饰静态的方法，锁住的是真个类
     */
    public static synchronized void staticout() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SafeThreadDemo3 safe1 = new SafeThreadDemo3();

        new Thread(() -> {
            safe1.staticout();
        }, "线程A").start();

        //synchronized修饰静态方法不同的实例仍然被锁住  不要使用synchronized修饰静态方法，因为会锁住整个类，会导致其他线程在等待这个锁
        new Thread(() -> {
            SafeThreadDemo3 safe2 = new SafeThreadDemo3();
            safe2.staticout();
        }, "线程B").start();
    }
}

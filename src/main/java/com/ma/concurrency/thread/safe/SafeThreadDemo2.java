package com.ma.concurrency.thread.safe;

public class SafeThreadDemo2 {

    /**
     * synchronized修饰普通的方法，锁住的是调用该方法的对象实例
     */
    public synchronized void out() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SafeThreadDemo2 safeThreadDemo2 = new SafeThreadDemo2();

        new Thread(() -> {
            safeThreadDemo2.out();
        },"线程A").start();


        new  Thread(()->{
            safeThreadDemo2.out();
        },"线程B").start();
    }
}

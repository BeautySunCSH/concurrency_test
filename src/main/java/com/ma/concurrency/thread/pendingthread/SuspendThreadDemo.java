package com.ma.concurrency.thread.pendingthread;

public class SuspendThreadDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"执行run方法，准备调用suspend方法");
        Thread.currentThread().suspend();
        System.out.println(Thread.currentThread().getName()+"执行run方法,调用suspend方法结束");
    }


    public static void main(String[] args) {
        Thread thread = new Thread(new SuspendThreadDemo());
        thread.start();
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.resume();

    }

}

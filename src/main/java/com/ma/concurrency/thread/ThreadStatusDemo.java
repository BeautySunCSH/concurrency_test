package com.ma.concurrency.thread;

public class ThreadStatusDemo {
    public static void main(String[] args) throws InterruptedException {

        //线程状态 RUNABLE（可运行状态）
        // new Thread(()->{
        //     try {
        //         System.in.read();
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // }).start();


        /*Object obj = new Object();
        new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(100000000000000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(2000L);


        //该线程等待上个线程释放obj的锁，处于BLOCKED(阻塞状态)
        new Thread(()->{
            synchronized (obj){

            }
        }).start();*/


        //WAITING(等待状态)

        /*Object object = new Object();
        new Thread(() -> {
            try {
                synchronized (object) {
                    object.wait();//Object 类的wait()方法，导致当前线程等待,直到其他线程notify()或notifyAll()
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/


//TIMED_WAITING 状态
        Object obj2 = new Object();
        new Thread(()->{
            synchronized (obj2){
                try {
                    //1分钟后，当前线程结束挂起状态
                    obj2.wait(60000L);
                    System.out.println(Thread.currentThread().getName()+"线程，1分钟后结束挂起状态");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

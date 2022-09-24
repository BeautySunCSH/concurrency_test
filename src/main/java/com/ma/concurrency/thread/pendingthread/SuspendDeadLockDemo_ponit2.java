package com.ma.concurrency.thread.pendingthread;

/**
 * <p>
 * 1、 supend()方法具有天然的死锁倾向
 * 2、当线程A被suspend后，该线程持有的锁obj1不会被释放，其他的线程就获取不到资源obj1
 * 3、当线程A被suspend后，如果resume的过程中出现异常导致resume方法执行失败，则lock锁无法释放，就会导致死锁
 * 4、如果这里不睡眠，可能resume方法限制性，然后线程后挂起  会造成死锁的情况
 * <p>
 * 在cmd敞口中使用jconole命令可以查看到对应的线程为死锁
 * </p>
 */
//本demo演示第2点
public class SuspendDeadLockDemo_ponit2 {
    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj1) {
                System.out.println("t1获取到obj1锁开始执行");
                try {
                    Thread.sleep(5000L);//模拟实际业务的执行过程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1执行结束");
            }
        }, "线程A");
        t1.start();

        Thread.sleep(3000L);
        t1.suspend();
        int i = 1 / 0;
        t1.resume();
        Thread t2 = new Thread(() -> {
            synchronized (obj2) {
                System.out.println("t2获取到obj2锁开始执行");
                try {
                    Thread.sleep(2000L);//模拟实际业务的执行过程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (obj1) {
                    System.out.println("t2获取obj1锁开始执行");
                }
                System.out.println("t2执行结束");
            }
        }, "线程B");
        t2.start();


        System.out.println(" ============================= ");

    }
}

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
//本demo演示第4点
public class SuspendDeadLockDemo_ponit4 implements Runnable {
    private static Object obj = new Object();

    @Override
    public void run() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + "占用资源");
            Thread.currentThread().suspend();
        }
        System.out.println(Thread.currentThread().getName() + "释放资源");
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SuspendDeadLockDemo_ponit4(), "对比线程");
        thread.start();
        Thread.sleep(1000L);
        thread.resume();

        Thread deadThread = new Thread(new SuspendDeadLockDemo_ponit4(), "死锁线程");
        deadThread.start();
        deadThread.resume();//如果这里不睡眠，可能resume方法限制性，然后线程后挂起  会造成死锁的情况
    }
}

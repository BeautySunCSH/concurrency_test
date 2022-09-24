package com.ma.concurrency.thread.priority;


/**
 * <p>
 * 在操作系统中，线程可以划分为优先级，线程优先级越高，获得CPU时间片的概率就越大，
 * 但线程的优先级的高低与线程的执行顺序没有必然的
 * 联系，优先级底的线程也有可能比优先级高的线程先执行
 * </p>
 * <p>
 * 线程的优先级分为 1~10 一共 10 个等级，所有线程默认优先级为 5，
 * 如果优先级小于 1 或大于 10，则会抛出 java.lang.IllegalArgumentException 异常。
 * </p>
 */
public class ThreadPriorityDemo {
    public static void main(String[] args) {
        System.out.println("线程main方法的优先级(改变前)" + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(9);
        System.out.println("线程main方法的优先级(改变后)" + Thread.currentThread().getPriority());

        Thread thread = new Thread(() -> {
            System.out.println("线程A的优先级为" + Thread.currentThread().getPriority());
        }, "线程A");
        thread.start();
        //输出内容：
        // 线程main方法的优先级(改变前)5
        // 线程main方法的优先级(改变后)9
        // 线程A的优先级为9
    }
}

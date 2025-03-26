package com.wenhao.thread.cyclic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numberOfThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads,
                () -> System.out.println("All parties have arrived at the barrier, let's proceed."));
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new Worker(barrier)).start();
        }
    }
}

class Worker implements Runnable {
    private final CyclicBarrier barrier;

    Worker(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is working...");
            Thread.sleep((long) (Math.random() * 1000)); // 模拟工作
            System.out.println(Thread.currentThread().getName() + " has finished. Waiting at the barrier.");
            barrier.await(); // 等待其他线程到达屏障点
            System.out.println(Thread.currentThread().getName() + " is proceeding.");
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }
}

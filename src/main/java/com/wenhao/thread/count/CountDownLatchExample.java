package com.wenhao.thread.count;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) {
        int numberOfWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numberOfWorkers);

        for (int i = 0; i < numberOfWorkers; i++) {
            new Thread(new Worker(latch)).start();
        }
        try {
            System.out.println("Main thread waiting for workers to finish...");
            latch.await(); // 主线程等待，直到计数器减到零
            System.out.println("All workers have finished. Main thread proceeding.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Worker implements Runnable {
    private final CountDownLatch latch;

    Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is working...");
            Thread.sleep((long) (Math.random() * 1000)); // 模拟工作
            System.out.println(Thread.currentThread().getName() + " has finished.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            latch.countDown(); // 工作线程完成任务后，计数器减一
        }
    }
}

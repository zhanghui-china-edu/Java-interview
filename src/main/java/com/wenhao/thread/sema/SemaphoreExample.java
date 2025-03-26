package com.wenhao.thread.sema;

import java.util.concurrent.Semaphore;

/**
 * 用于控制对共享资源的访问数量。它可以用于限制同时访问某些资源的线程数量，从而实现流量控制、资源管理等功能。
 * - 1、指定许可的数量，即同时允许访问资源的最大线程数。Semaphore semaphore = new Semaphore(3);
 * - 2、线程在访问资源前需要获取许可。如果没有可用的许可，线程将被阻塞，直到有许可可用。semaphore.acquire();
 * - 3、线程在访问资源后需要释放许可，以便其他线程可以获取许可。semaphore.release();
 */
public class SemaphoreExample {
    private static final int MAX_CONCURRENT_THREADS = 3;
    private static final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_THREADS);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Worker(i)).start();
        }
    }

    static class Worker implements Runnable {
        private final int id;

        Worker(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                // 获取一个许可
                semaphore.acquire();
                System.out.println("Thread " + id + " is working.");
                // 模拟工作
                Thread.sleep(2000);
                System.out.println("Thread " + id + " has finished.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // 释放许可
                semaphore.release();
            }
        }
    }
}

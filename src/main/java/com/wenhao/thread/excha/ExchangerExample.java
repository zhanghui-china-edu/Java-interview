package com.wenhao.thread.excha;

import java.util.concurrent.Exchanger;

/**
 * 用于在两个线程之间交换数据。它提供了一种简单的机制，使两个线程可以在某个同步点交换彼此的数据。
 */
public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread thread1 = new Thread(new Task(exchanger, "Data from Thread 1"), "Thread 1");
        Thread thread2 = new Thread(new Task(exchanger, "Data from Thread 2"), "Thread 2");

        thread1.start();
        thread2.start();
    }
}

class Task implements Runnable {
    private Exchanger<String> exchanger;
    private String data;

    Task(Exchanger<String> exchanger, String data) {
        this.exchanger = exchanger;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is exchanging data: " + data);
            String receivedData = exchanger.exchange(data);
            System.out.println(Thread.currentThread().getName() + " received data: " + receivedData);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

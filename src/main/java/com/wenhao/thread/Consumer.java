package com.wenhao.thread;

import java.util.List;

public class Consumer extends Thread {

    public Consumer(List<String> goods) {
        this.goods = goods;
    }

    private final List<String> goods;

    @Override
    public void run() {
        //  Sychronized 版本  生产者消费者必须使用同一个锁对象，不然无法唤醒
        while (true) {
            synchronized (goods) {
                if (goods.isEmpty()) {
                    try {
                        goods.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                String remove = goods.remove(0);
                System.out.printf("消费者消费了数据%s%n", remove);
                //  唤醒必须持有锁
                goods.notify();
            }
        }
    }
}

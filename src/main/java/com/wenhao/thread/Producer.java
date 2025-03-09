package com.wenhao.thread;

import java.util.List;

public class Producer extends Thread {

    public Producer(List<String> goods) {
        this.goods = goods;
    }

    private final List<String> goods;

    @Override
    public void run() {
        while (true) {
            //  Sychronized 版本  生产者消费者必须使用同一个锁对象，不然无法唤醒
            synchronized (goods) {
                if (goods.size() >= 5) {
                    try {
                        goods.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                goods.add("goods...");
                System.out.println("生产者生产了一个商品，当前商品数量为：" + goods.size());
                goods.notifyAll();
            }
        }
    }
}

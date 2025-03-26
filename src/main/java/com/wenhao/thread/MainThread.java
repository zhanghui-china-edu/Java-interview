package com.wenhao.thread;

import java.util.ArrayList;
import java.util.List;

public class MainThread {

    private static final List<String> goods = new ArrayList<>(5);

    public static void main(String[] args) {
        Thread p1 = new Thread(() -> {
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
        });

        Thread c1 = new Thread(() -> {
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
        });
        p1.start();
        c1.start();
    }
}

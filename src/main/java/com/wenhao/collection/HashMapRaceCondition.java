package com.wenhao.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapRaceCondition {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> map = new ConcurrentHashMap<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, "Thread1-" + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, "Thread2-" + i);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Map size: " + map.size());
        int count = 0;
        for (Integer ignored : map.keySet()) {
            count++;
        }
        System.out.println("Counted size: " + count);
    }
}

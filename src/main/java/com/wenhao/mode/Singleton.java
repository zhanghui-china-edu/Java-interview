package com.wenhao.mode;

public class Singleton {
    //实现一个线程安全的单例模式（要求：懒加载、高效）
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance1);
    }
}

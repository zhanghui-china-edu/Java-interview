package com.wenhao;

import com.wenhao.thread.Consumer;
import com.wenhao.thread.Producer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> goods = new ArrayList<>(5);

        Thread p1 = new Producer(goods);
        Thread c1 = new Consumer(goods);

        p1.start();
        c1.start();
    }
}
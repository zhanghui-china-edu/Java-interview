package com.wenhao.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<String> goods = new ArrayList<>(5);

        Map<String,String> map = new HashMap<>(5);

        Thread p1 = new Producer(goods);
        Thread c1 = new Consumer(goods);

        p1.start();
        c1.start();
    }
}

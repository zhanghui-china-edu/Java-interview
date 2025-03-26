package com.wenhao.algorithm;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {3, 6, 8, 10, 1, 2, 1};

    }


    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}

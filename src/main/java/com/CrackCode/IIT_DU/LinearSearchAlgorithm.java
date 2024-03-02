package com.CrackCode.IIT_DU;

public class LinearSearchAlgorithm {
    public static int linearSearch(int[] arr, int key) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] a1 = {10, 20, 30, 50, 70, 90};
        int key = 540;

        int index = linearSearch(a1, key);
        if (index != -1) {
            System.out.println("Item found at index : " + index);
            return;
        }
        System.out.println("Item not found");
    }
}
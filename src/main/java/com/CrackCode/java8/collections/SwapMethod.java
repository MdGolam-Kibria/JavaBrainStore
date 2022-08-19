package com.CrackCode.java8.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SwapMethod {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        System.out.println("Original List: " + list);
        Collections.swap(list, 0, list.size() - 1);
        System.out.println("Swapped List: " + list);
        /*
        Output:
        Original List: [a, b, c, d, e]
        Swapped List: [e, b, c, d, a]
         */
    }
}
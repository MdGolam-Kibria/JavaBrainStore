package com.CrackCode.java8.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FrequencyMethod {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "a", "b", "b");
        System.out.println("Original List: " + list);
        System.out.println("Frequency of 'a': " + Collections.frequency(list, "a"));
        System.out.println("Frequency of 'b': " + Collections.frequency(list, "b"));
        /*
        Output:
        Original List: [a, b, c, d, e]
        Frequency of 'a': 2
        Frequency of 'b': 3
         */
    }
}

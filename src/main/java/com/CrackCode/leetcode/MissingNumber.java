package com.CrackCode.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissingNumber {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 6, 20, 9);
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0, min = 0;
        for (int current : list) {
            if (current > max)
                max = current;
            if (current < min)
                min = current;
            map.put(current, current);
        }
        for (int i = 1; i <= max; i++) {
            Integer current = map.get(i);
            if (current == null)
                System.out.println(i);
        }
    }
}

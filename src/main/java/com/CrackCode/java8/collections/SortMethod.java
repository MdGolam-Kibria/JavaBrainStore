package com.CrackCode.java8.collections;

import java.util.*;

public class SortMethod {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "d", "e", "b", "c");
        System.out.println("Original List: " + list);
        Collections.sort(list);//by default sorted it by ascending order
        System.out.println("Sorted List: " + list);
        /*
        Output:
        Original List: [a, b, c, d, e]
        Sorted List: [a, b, c, d, e]
         */



        ///Now going to compare number as descending order using comparator
        List<Integer> list2 = Arrays.asList(1, 6, 4, 4, 2, 5, 3);
        System.out.println("\n\nOriginal List: " + list2);
        Collections.sort(list2, new ComparatorForDescendingNumber());//sort by descending order
        System.out.println("Sorted List: " + list2);
        /*
        Output:
        Original List: [1, 6, 4, 4, 2, 5, 3]
        Sorted List: [6, 5, 4, 4, 3, 2, 1]
         */



        ///Now going to compare number as ascending order using comparator
        List<Integer> list3 = Arrays.asList(1, 6, 4, 4, 2, 5, 3);
        System.out.println("\n\nOriginal List: " + list3);
        Collections.sort(list2, new ComparatorForAscendingNumber());//sort by ascending order
        System.out.println("Sorted List: " + list3);
        /*
        Output:
        Original List: [1, 6, 4, 4, 2, 5, 3]
        Sorted List: [1, 6, 4, 4, 2, 5, 3]
         */
    }
}

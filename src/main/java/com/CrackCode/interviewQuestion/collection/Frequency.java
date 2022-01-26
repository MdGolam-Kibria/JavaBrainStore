package com.CrackCode.interviewQuestion.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Frequency {
    public static void main(String[] args) {
        /**
         * find how many same data in a list using Collections frequency
         */
        List<Integer> list = Arrays.asList(6,3,1,2,4,2,5,8,6,6,6,4,5,3);
        int frequency = Collections.frequency(list,/*findable number*/6);
        System.out.println(frequency);

        /**
         * count sum of the list
         */
        double sum = list.parallelStream().mapToLong(value -> value).sum();
        System.out.println(sum);
        /**
         * count average of the list
         * here code comment only for ava version
         */
       /* var average = list.parallelStream().mapToInt(value -> value).average();
        System.out.println(average.getAsDouble());*/
    }
}

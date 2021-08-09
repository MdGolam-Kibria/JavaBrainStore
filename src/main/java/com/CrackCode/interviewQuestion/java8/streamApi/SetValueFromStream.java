package com.CrackCode.interviewQuestion.java8.streamApi;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SetValueFromStream {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(6, 3, 1, 2, 4, 2, 5, 8, 6, 6, 6, 4, 5, 3);
        final String[] b = {"ok"};
        AtomicInteger data = new AtomicInteger();
        list.stream().skip(2).map(integer -> {
            System.out.printf("\t" + integer);
            b[0] = "wow";//change the value from here
            increment(data);
            return integer;
        }).count();
        System.out.println(b[0]);
        System.out.println(data.get());
    }

    private static void increment(AtomicInteger data) {
        data.getAndIncrement();//increment the value using increment
        System.out.println(""+data.get());
    }
}

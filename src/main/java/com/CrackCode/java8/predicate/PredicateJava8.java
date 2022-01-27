package com.CrackCode.java8.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateJava8 {
    /**
     * remove boilerplate code from [Stream] filter condition using Predicate beauty
     */
    public static void main(String[] args) {

        Predicate<Integer> andNotEqual7 = x -> x != 7;
        Predicate<Integer> noGreaterThan5 = x -> x > 5 && x != 10;

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        /**
         * Predicate and() beauty
         */
        List<Integer> collect = list.stream()
                .filter(noGreaterThan5.and(andNotEqual7))//here apply and() for add more than 1 Predicate conditional function
                .collect(Collectors.toList());

        System.out.println("AndTest = " + collect);

        /**
         * Predicate or() beauty
         */
        Predicate<Integer> noLessThen5 = value -> value == 5;
        Predicate<Integer> graterThen2 = value -> value < 2;
        List<Integer> orCollect = list.stream()
                .filter(noLessThen5.or(graterThen2))//here apply or() for apply or conditional functionality
                .collect(Collectors.toList());
        System.out.println("OrTest = " + orCollect);


        /**
         * Predicate negate() beauty for apply not statement feature
         */
        Predicate<String> startWithA = x -> x.startsWith("A");

        List<String> TestList = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");

        List<String> testCollect = TestList.stream()////here apply negate() for apply not statement like ! [a!=null] or conditional functionality
                .filter(startWithA.negate())//here print all line without string which start with "A"
                .collect(Collectors.toList());

        System.out.println("negate = " + testCollect);


        /**
         * Predicate test() beauty for apply not statement feature
         */
        Predicate<Integer> isNotEqual0 = x -> x == 0;
        System.out.println("test method check = "+isNotEqual0.test(Integer.parseInt("0")));


    }
}

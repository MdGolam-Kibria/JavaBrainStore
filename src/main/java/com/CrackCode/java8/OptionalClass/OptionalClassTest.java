package com.CrackCode.java8.OptionalClass;

import java.util.Optional;
import java.util.function.Predicate;

public class OptionalClassTest {
    public static void main(String[] args) {
        /* Optional<String> fastTest = Optional.of(null);//this will throw error(NullPointerException) cause optional of(); method didn't get null value
        System.out.println(fastTest.get());*/
        //for avoid nullPointer exception use can use
        Optional<String> sndTest = Optional.ofNullable(null);//this works properly will check is present or not
        System.out.println(sndTest.orElse("This is null"));///check null and set default value

//        Now works with optional filter
        Predicate<Integer> isLessThen20 = value->value<20;
        Optional<Integer> filterTest = Optional.of(15)
                .filter(isLessThen20);
        System.out.println("This is Optional filter test = "+ filterTest.get());





    }
}
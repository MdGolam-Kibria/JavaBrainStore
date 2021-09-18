package com.CrackCode.interviewQuestion.java8.java8Function;


import java.util.function.Function;

public class Java8FunctionTest {

    public static void main(String[] args) {
        Function<Integer, Integer> f1 = i -> i * 4;
        Function<Integer, Integer> f2 = i -> i + 4;
        /*
         here first hit to the f1 function after get result hit f2 function
         */
        System.out.println(f2/*snd hit*/.compose(f1/* fast hit*/).apply(3)); // 16 expected
        /*
           Here first check f2 after get result from f2 then hit f1 based on the result
         */
        System.out.println(f2.andThen(f1).apply(3));//28 expected
    }
}

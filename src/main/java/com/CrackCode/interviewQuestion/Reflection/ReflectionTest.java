package com.CrackCode.interviewQuestion.Reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ReflectionTest {
    public static void main(String[] args) {
        System.out.println("Get All Method Name Of A Object = " + new ReflectionTest().getAllMethodOfAObject(new Person()));
        System.out.println("\nGet All Field Name of a Object = " + new ReflectionTest().getAllFieldNameOfAObject(new Person()));
        /***
         * works with real life example in below path example :) :)
         * interviewQuestion\src\main\java\com\CrackCode\interviewQuestion\database\entityManagerHelper\EntityManagerQueryHelper.java
         */
    }

    public Collection getAllMethodOfAObject(Object expectedObject) {
        return Arrays
                .stream(expectedObject.getClass().getMethods())
                .map(method -> method.getName())
                .collect(Collectors.toList());
    }

    public Collection getAllFieldNameOfAObject(Object expectedObject) {
        return Arrays
                .stream(expectedObject.getClass().getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }


}

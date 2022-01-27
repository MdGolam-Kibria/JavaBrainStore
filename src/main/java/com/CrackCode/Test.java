package com.CrackCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public boolean isEmpty(Object objectList) {
        return true;
    }


    public static void main(String[] args) {
        List<String> objectList = new ArrayList<>();
        objectList.addAll(Arrays.asList("a", "b", "c", "d"));


        new Test().isEmpty(new ArrayList<>());

    }
}

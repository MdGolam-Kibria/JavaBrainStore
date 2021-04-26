package com.CrackCode.interviewQuestion.designPattern.BuilderPattern;

import java.util.Arrays;

public class BuilderPatternResult {
    public static void main(String[] args) {
        Programmer programmer = Programmer.builder()
                .id(Long.parseLong("1"))
                .name("Golam Kibria")
                .favouriteLanguages(Arrays.asList("Java","Python","TypeScript"))
                .skilledFrameworkName("Spring boot")
                .numberOfExperience(2)
                .build();

        /**
         * after set data to object print the object
         */
        System.out.println(programmer);
    }
}

package com.CrackCode.java8.streamApi;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;

public class StreamStatisticsTest {
    public static void main(String[] args) {
        List<PersonStream> personStreamList = Arrays.asList(
                new PersonStream("1", "sayem", "25", 12.12),
                new PersonStream("2", "helal", "25", 120.22),
                new PersonStream("3", "rosni", "14", 120.22),
                new PersonStream("4", "ashraf", "25", 15.12),
                new PersonStream("5", "rasel", "29", 90.02),
                new PersonStream("6", "kibria", "27", 500.02),
                new PersonStream("7", "jahid", "26", 5.00)
        );


        //-------------------Statistics---------------------------------
        System.out.println("Statistics example 1");
        /**
         * topic =  Statistics
         * get max,min,sum and Average from a  list;
         */
        List numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt(value -> (int) value).summaryStatistics();
        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());


        //example 2 below
        System.out.println("Statistics example \n\n");

        DoubleSummaryStatistics personSalaryList = personStreamList
                .stream()
                .mapToDouble(personStream -> personStream.getSalary()).summaryStatistics();
        System.out.println("Highest Salary in List : " + personSalaryList.getMax());
        System.out.println("Lowest Salary in List : " + personSalaryList.getMin());
        System.out.println("Sum of all Salary : " + personSalaryList.getSum());
        System.out.println("Average of all Salary : " + personSalaryList.getAverage());

    }
}

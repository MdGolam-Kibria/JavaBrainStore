package com.CrackCode.interviewQuestion.streamApi.loop;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamApiTest {
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

        //-------------------------------------filter()------------------------------------
        /**
         * topic = filter();
         * get all salary from a PersonStream[Person-say] where salary greater than 20
         */
        personStreamList
                .stream()
                .map(PersonStream::getSalary)
                .filter(salary -> salary >= 20)
                .collect(Collectors.toList())
                .forEach(System.out::println);


        //----------------------------------distinct()---------------------------------------
        /**
         * topic = distinct();
         * now get all salary from a PersonStream[Person-say] where salary greater than or equal 20 and all salary should be unique
         */
        System.out.println("distinct() method check");
        personStreamList
                .stream()
                .map(PersonStream::getSalary)
                .filter(salary -> salary >= 20)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);


        //---------------------------------count()----------------------------------------
        /**
         * topic = count();
         * count number of person from a PersonStream[Person-say] where salary lower than 20 or equal
         */
        System.out.println("count() method check");

        long totalPerson = personStreamList
                .stream()
                .map(PersonStream::getSalary)
                .filter(aDouble -> aDouble <= 20)
                .count();
        System.out.println("Total Person = " + totalPerson);


        //-----------------------------------max() and min()--------------------------------------
        /**
         * topic = max() and min();
         * detect maximum and minimum salary of person from a PersonStreamList
         */
        System.out.println("max() method check");

        PersonStream maxSalaryPerson = personStreamList
                .stream()
                .max((o1, o2) -> o1.getSalary() > o2.getSalary() ? 1 : -1)
                .get();
        System.out.println("Maximum Salary Person = " + maxSalaryPerson.getSalary() + " \n her name = " + maxSalaryPerson.getName());


        //for min() method
        System.out.println("min() method check\n");
        PersonStream minSalaryPerson = personStreamList
                .stream()
                .max((o1, o2) -> o1.getSalary() < o2.getSalary() ? 1 : -1)
                .get();
        System.out.println("Minimum Salary Person = " + minSalaryPerson.getSalary() + " \n her name = " + minSalaryPerson.getName());


        //-------------------------collect()-----------------------------
        /**
         * topic = collect()
         * collect all person name where salary less then 100 and greater then 10
         */
        personStreamList
                .stream()
                .filter(personStream -> personStream.getSalary() < 100 && personStream.getSalary() > 10)
                .collect(Collectors.toList())
                .forEach(personStream -> System.out.println("FilterPersonName = " + personStream.getName()));


        //--------------Collectors joining()--------------------------------

        /**
         * topic = remove empty array from array list and merge into a array using [{Collectors.joining()}] method
         */

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("Filtered List: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged String: " + mergedString);








    }
}

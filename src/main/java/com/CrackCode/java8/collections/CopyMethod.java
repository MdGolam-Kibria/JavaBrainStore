package com.CrackCode.java8.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CopyMethod {
    public static void main(String[] args)
            throws Exception
    {
        try {

            // creating object of Source list and destination List
            List<String> srclst = Arrays.asList("a", "b", "c", "d", "e");
            List<String > destlst = Arrays.asList("1", "2", "3", "4", "0","newValue");

            // printing the srclst
            System.out.println("Value of source list: " + srclst);

            // printing the destlst
            System.out.println("Value of destination list: " + destlst);

            System.out.println("\nAfter copying:\n");

            // copy element into destlst
            Collections.copy(destlst, srclst);

            // printing the srclst
            System.out.println("Value of source list: " + srclst);

            // printing the destlst
            System.out.println("Value of destination list: " + destlst);
            //Note: The above code will throw an exception if the destination list is not big enough to hold all the elements of the source list.

        }catch (Exception e) {
            System.out.println(e);
        }
    }
}

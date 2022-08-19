package com.CrackCode.java8.collections;

import java.util.Comparator;
/*
Ascending Order
public int compare(Shop s1, Shop s2)
    {
        return s1.name.compareTo(s2.name);
    }

Descending Order
public int compare(Shop s1, Shop s2)
    {
        return s2.name.compareTo(s1.name);
    }
 */
public class ComparatorForAscendingNumber implements Comparator<Integer> {
    @Override
    public int compare(Integer val1, Integer val2) {
        return val1.compareTo(val2);
        //We can use below code as well for ascending order instead of above code
    /*    if (Objects.equals(val1, val2)) {
            return 0;//If we return 0 Comparator will detect both value is equal and will not go to next Comparator and comparator will get any value from both value
        } else if (val1 > val2) {
            return 1;
        }
        return -1;
    */
    }
}

package com.CrackCode.IIT_DU.DS.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Remember one thing that the Stack concept
 * always work in [LIFO(last in first out || FILO (first in last out)] model
 */
public class StackUsingList {
    private List<Integer> head;


    public StackUsingList() {
        this.head = new ArrayList<>();
    }

    private void push(Integer data) {
        head.add(data);
    }

    private Integer pop() {
        if (head.isEmpty()) throw new RuntimeException("Empty stack");
        int expectedSize = head.size() - 1;
        int expectedVal = head.get(expectedSize);
        head.remove(expectedSize);
        return expectedVal;
    }

    private Integer peek() {
        if (head.isEmpty()) throw new RuntimeException("Empty stack");
        return head.get(head.size() - 1);
    }

    public static void main(String[] args) {
        StackUsingList list = new StackUsingList();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        do {
            System.out.println(list.peek());
            list.pop();
        } while (!list.head.isEmpty());
    }

}

package com.CrackCode.IIT_DU.DS.Stack;

public class StackUsingCoreArray {
    private final int DEFAULT_SIZE = 10;
    private int currentSize;
    private int[] head;

    public StackUsingCoreArray() {
        head = new int[DEFAULT_SIZE];
        currentSize = 0;
    }

    private void push(int data) {
        if (currentSize == head.length) {
            int[] newArray = new int[head.length * 2];
            System.arraycopy(head, 0, newArray, 0, head.length);
            head = newArray;
        }
        head[currentSize++] = data;
    }


    private int pop() {
        if (currentSize == 0) throw new RuntimeException("Empty Stack");
        int expectedVal = head[--currentSize];
        int[] newArr = new int[currentSize];
        System.arraycopy(head, 0, newArr, 0, currentSize);
        head = newArr;
        return expectedVal;
    }

    private int peek() {
        if (currentSize == 0) throw new RuntimeException("Empty Stack");
        return head[currentSize - 1];
    }


    public static void main(String[] args) {
        StackUsingCoreArray array = new StackUsingCoreArray();
        array.push(1);
        array.push(2);
        array.push(3);
        array.push(4);

        do {
            System.out.println(array.peek());
            array.pop();
        } while (array.head.length != 0);
    }
}

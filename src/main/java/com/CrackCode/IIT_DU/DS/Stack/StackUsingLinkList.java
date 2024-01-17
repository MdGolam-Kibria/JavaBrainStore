package com.CrackCode.IIT_DU.DS.Stack;

public class StackUsingLinkList {
    /**
     * Remember one thing that the Stack concept always work in [LIFO(last in first out)] model
     */
    private Node head;

    public Node getHead() {
        return head;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        newNode.setNext(head);
        head = newNode;
    }

    public int pop() {
        if (head == null) throw new RuntimeException("Empty Stack");
        int topData = head.getData();
        head = head.getNext();
        return topData;
    }

    public int peek() {
        if (head == null) throw new RuntimeException("Empty Stack");
        return head.getData();
    }

/*    public static void main(String[] args) {
        //@INFO FOR TEST THE STACK IMPLEMENTATION
        StackUsingLinkList stack = new StackUsingLinkList();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        do {
            System.out.println(stack.peek());
            stack.pop();
        } while (stack.getHead() != null);
    }*/
}

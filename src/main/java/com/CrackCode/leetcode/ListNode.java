package com.CrackCode.leetcode;

public class ListNode {
    int val;
    ListNode next = null;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    // Method to add multiple values
    public void addValues(ListNode listNode, int[] values) {
        while (listNode.next != null) {
            listNode = listNode.next;
        }
        for (int value : values) {
            listNode.next = new ListNode(value);
            listNode = listNode.next;
        }
    }

    public void printList(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + " -> ");
            listNode = listNode.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        int[] valuesToAdd = {2, 3, 4, 5};
        head1.addValues(head1, valuesToAdd);
        head1.printList(head1);


        System.out.println();
        System.out.println();

        ListNode head2 = new ListNode(1);
        head2.addValues(head2, valuesToAdd);
        head2.printList(head2);
    }
}
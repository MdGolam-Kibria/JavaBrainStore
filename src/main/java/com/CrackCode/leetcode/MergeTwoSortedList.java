package com.CrackCode.leetcode;

public class MergeTwoSortedList {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummyHead = new ListNode();

        ListNode current = dummyHead;


        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;

                list2 = list2.next;
            }
            current = current.next;
        }
        current.next = (list1 == null) ? list2 : list1;
        return dummyHead.next;
    }


    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        int[] valuesToAdd = {2, 3, 4, 5};
        head1.addValues(head1, valuesToAdd);
        head1.printList(head1);
        ListNode head2 = new ListNode(1);
        head2.addValues(head2, valuesToAdd);
        head2.printList(head2);
        System.out.println("\n");
        //mergedSortedList = https://leetcode.com/problems/merge-two-sorted-lists/description/
        ListNode mergedSortedList = MergeTwoSortedList.mergeTwoLists(head1, head2);
        System.out.println("Merged Sorted List: ");
        head1.printList(mergedSortedList);
    }
}

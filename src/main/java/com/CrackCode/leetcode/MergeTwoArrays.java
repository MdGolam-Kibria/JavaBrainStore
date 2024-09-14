package com.CrackCode.leetcode;

public class MergeTwoArrays {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {4, 5, 6};
        int[] mergedArr = new int[nums1.length + nums2.length];


        for (int i = 0; i < mergedArr.length; i++) {
            if (i <= nums1.length - 1) {
                mergedArr[i] = nums1[i];
            } else
                mergedArr[i] = nums2[i - nums1.length];
        }


        for (int j : mergedArr) {
            System.out.println(j);
        }

    }
}

package com.CrackCode.IIT;

import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter a number : ");

        int expectedNumber = myObj.nextInt();
        System.out.println("Using Builder: "+reverseNumber(expectedNumber));
        reverseNumber2(expectedNumber);
    }

    private static int reverseNumber(int expectedNumber) {
        StringBuilder builder = new StringBuilder(String.valueOf(expectedNumber));
        return Integer.parseInt(builder.reverse().toString());
    }

    private static void reverseNumber2(int expectedNumber) {
        System.out.print("Using Array: ");
        char[] charArray = String.valueOf(expectedNumber).toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            System.out.print(""+charArray[i]);
        }
    }
}

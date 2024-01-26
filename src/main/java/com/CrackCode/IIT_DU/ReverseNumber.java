package com.CrackCode.IIT_DU;

import java.util.Scanner;

public class ReverseNumber {
    private static int reverseNumber(int expectedNumber) {
        StringBuilder builder = new StringBuilder(String.valueOf(expectedNumber));
        return Integer.parseInt(builder.reverse().toString());
    }

    private static void reverseNumber2(int expectedNumber) {
        System.out.print("Using Array: ");
        char[] charArray = String.valueOf(expectedNumber).toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            System.out.print("" + charArray[i]);
        }
    }


    public static void main(String[] args) {
        System.out.println("Enter your number: ");
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Input should be an integer");
            main(new String[]{});
        } else {
            int currentVal = scanner.nextInt();
            reverseNumber(currentVal);
            reverseNumber2(currentVal);
            System.out.println();
        }

    }
}

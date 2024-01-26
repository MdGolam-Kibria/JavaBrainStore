package com.CrackCode.IIT_DU;

import java.util.Scanner;

public class Pyramid {
    private static void printNumberPyramidUsingStringBuilder(int currentVal) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= currentVal; i++) {
            for (int j = 0; j < currentVal - i; j++) {
                builder.append("  ");
            }
            for (int j = i; j >= 1; j--) {
                builder.append(j).append(" ");
            }
            for (int k = 2; k <= i; k++) {
                builder.append(k).append(" ");
            }
            System.out.println(builder);
            builder.setLength(0);
        }
    }

    private static void printNumberPyramid(int currentVal) {
        for (int i = 1; i <= currentVal; i++) {
            for (int j = 0; j < currentVal - i; j++) {
                System.out.print("  ");
            }
            for (int j = i; j >= 1; j--) {
                System.out.print(j + " ");
            }
            for (int k = 2; k <= i; k++) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        System.out.println("Enter your number: ");
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.err.println("Input should be an integer..");
            main(new String[]{});
        } else {
            int currentVal = scanner.nextInt();
            printNumberPyramid(currentVal);
            System.out.println("\nUsing String Builder : ");
            printNumberPyramidUsingStringBuilder(currentVal);
        }

    }
}

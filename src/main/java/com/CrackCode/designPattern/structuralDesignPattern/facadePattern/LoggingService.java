package com.CrackCode.designPattern.structuralDesignPattern.facadePattern;

public class LoggingService {
    public void makeEntry(String cardNumber, double amount, String operationType) {
        System.out.println("\nLOG:-\nDoing transaction using \nCard Number: " + cardNumber + ",\nAmount: " + amount + ",\nType: " + operationType + "\n");
    }
}
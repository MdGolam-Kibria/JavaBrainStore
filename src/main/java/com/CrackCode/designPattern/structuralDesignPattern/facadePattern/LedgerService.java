package com.CrackCode.designPattern.structuralDesignPattern.facadePattern;

public class LedgerService {
    public void makeEntry(String cardNumber, double amount, String operationType) {
        System.out.println("Card Number: " + cardNumber + ",\nAmount: " + amount + ",\nType: " + operationType);
    }
}
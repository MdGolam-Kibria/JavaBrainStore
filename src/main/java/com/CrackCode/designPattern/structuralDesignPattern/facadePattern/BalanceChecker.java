package com.CrackCode.designPattern.structuralDesignPattern.facadePattern;

public class BalanceChecker {
    public boolean hasSufficientBalance(String cardNumber, double amount) {
        System.out.println("Checking balance for card: " + cardNumber + " and amount :" + amount);
        return true;
    }
}
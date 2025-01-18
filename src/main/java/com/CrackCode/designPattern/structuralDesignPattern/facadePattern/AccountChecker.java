package com.CrackCode.designPattern.structuralDesignPattern.facadePattern;

public class AccountChecker {
    public boolean isAccountActive(String cardNumber) {
        System.out.println("Checking account is active for card: " + cardNumber);
        return true;
    }
}
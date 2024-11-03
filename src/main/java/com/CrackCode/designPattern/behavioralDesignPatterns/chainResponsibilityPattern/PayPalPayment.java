package com.CrackCode.designPattern.behavioralDesignPatterns.chainResponsibilityPattern;

public class PayPalPayment extends PaymentHandler {
    @Override
    public void handlePayment(double amount) {
        if (amount <= 1500) {
            System.out.println("Paid amount using PayPal and the amount is  : " + amount);
        } else {
            System.out.println("Requested amount is exceeds the limit. Please try to pay  within your limit.");
        }
    }
}

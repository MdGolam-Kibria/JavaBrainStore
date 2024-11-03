package com.CrackCode.designPattern.behavioralDesignPatterns.chainResponsibilityPattern;

public class CreditCardPayment extends PaymentHandler {
    @Override
    public void handlePayment(double amount) {
        if (amount <= 1000) {
            System.out.println("Paid using credit card and the amount is :" + amount);
        } else {
            next.handlePayment(amount);
        }
    }
}

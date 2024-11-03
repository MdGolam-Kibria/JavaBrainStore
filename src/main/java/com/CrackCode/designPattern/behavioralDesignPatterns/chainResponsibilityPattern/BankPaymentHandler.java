package com.CrackCode.designPattern.behavioralDesignPatterns.chainResponsibilityPattern;

public class BankPaymentHandler extends PaymentHandler {
    @Override
    public void handlePayment(double amount) {
        if (amount <= 500) {
            System.out.println("Paid using bank account and the amount is  : " + amount);
        } else {
            next.handlePayment(amount);
        }
    }
}

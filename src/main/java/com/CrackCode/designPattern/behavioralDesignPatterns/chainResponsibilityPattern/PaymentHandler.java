package com.CrackCode.designPattern.behavioralDesignPatterns.chainResponsibilityPattern;

public abstract class PaymentHandler {
    protected PaymentHandler next;

    public void setNext(PaymentHandler next) {
        this.next = next;
    }

    public abstract void handlePayment(double amount);
}

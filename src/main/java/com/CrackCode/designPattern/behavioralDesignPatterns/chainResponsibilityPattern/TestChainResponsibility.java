package com.CrackCode.designPattern.behavioralDesignPatterns.chainResponsibilityPattern;

public class TestChainResponsibility {
    public static void main(String[] args) {
        PaymentHandler bankPayment = new BankPaymentHandler();
        PaymentHandler creditCardPayment = new CreditCardPayment();
        PaymentHandler payPalPayment = new PayPalPayment();

        bankPayment.setNext(creditCardPayment);
        creditCardPayment.setNext(payPalPayment);

        bankPayment.handlePayment(500);
        bankPayment.handlePayment(1000);
        bankPayment.handlePayment(1001);
        bankPayment.handlePayment(2000);
    }
}

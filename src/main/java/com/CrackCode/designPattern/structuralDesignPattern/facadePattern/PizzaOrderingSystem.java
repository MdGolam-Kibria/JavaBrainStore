package com.CrackCode.designPattern.structuralDesignPattern.facadePattern;

public class PizzaOrderingSystem {
    public static void main(String[] args) {
        PaymentFacade paymentFacade = new PaymentFacade();


        String cardNumber = "1234-5678-9876-5432";
        String pin = "1234";
        double amount = 25.99;
        String operationType = "Pizza Order";


        boolean isPaymentSuccessful = paymentFacade.makePayment(cardNumber, pin, amount, operationType);

        if (isPaymentSuccessful) {
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Order failed. Please try again.");
        }
    }
}

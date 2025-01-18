package com.CrackCode.designPattern.structuralDesignPattern.facadePattern;

public class NotificationService {
    public void sendNotification(String cardNumber, String message) {
        System.out.println("\nNotification sent to card:" + cardNumber + "\n" + message);
    }
}
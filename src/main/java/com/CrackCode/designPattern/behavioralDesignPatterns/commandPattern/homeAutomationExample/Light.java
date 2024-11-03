package com.CrackCode.designPattern.behavioralDesignPatterns.commandPattern.homeAutomationExample;

// Receiver Classes
class Light {
    public void turnOn() {
        System.out.println("The light is ON");
    }

    public void turnOff() {
        System.out.println("The light is OFF");
    }
}
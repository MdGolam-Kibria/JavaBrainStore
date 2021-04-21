package com.CrackCode.interviewQuestion.Polymorphism.designPattern.SingletonPattern;

public class SingletonEger {
    private static SingletonEger singletonEagerInstance = new SingletonEger();

    private SingletonEger() {
    }

    public static SingletonEger getInstance() {
        return singletonEagerInstance;
    }
}

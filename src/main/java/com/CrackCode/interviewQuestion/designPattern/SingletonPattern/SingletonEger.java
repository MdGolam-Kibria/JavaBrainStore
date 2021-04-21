package com.CrackCode.interviewQuestion.designPattern.SingletonPattern;

public class SingletonEger {
    private static SingletonEger singletonEagerInstance = new SingletonEger();

    private SingletonEger() {
    }

    public static SingletonEger getInstance() {
        return singletonEagerInstance;
    }
}

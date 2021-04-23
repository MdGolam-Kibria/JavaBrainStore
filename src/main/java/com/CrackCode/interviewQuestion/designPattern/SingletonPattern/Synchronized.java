package com.CrackCode.interviewQuestion.designPattern.SingletonPattern;

public class Synchronized {
    public static Synchronized instance;

    private Synchronized() {
    }

    public synchronized Synchronized getInstance() {
        if (instance == null) {
            instance = new Synchronized();
        }
        return instance;
    }
}

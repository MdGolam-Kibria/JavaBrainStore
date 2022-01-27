package com.CrackCode.designPattern.SingletonPattern;

public class SingletonDoubleCheckLocking {
    private SingletonDoubleCheckLocking() {
    }

    private static volatile SingletonDoubleCheckLocking instance = null;

    public static SingletonDoubleCheckLocking getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleCheckLocking.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheckLocking();
                }
            }
        }
        return instance;
    }
}

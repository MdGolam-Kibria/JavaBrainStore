package com.CrackCode.designPattern.SingletonPattern;

public class SingletonLazy {
    private static SingletonLazy singletonLazyInstance;

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        if (singletonLazyInstance == null) {
            singletonLazyInstance = new SingletonLazy();
        }
        return singletonLazyInstance;
    }
}

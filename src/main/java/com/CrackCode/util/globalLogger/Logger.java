package com.CrackCode.util.globalLogger;

public class Logger {
    // The single instance of Logger (volatile for thread safety in multithreaded environments)
    private static volatile Logger instance;

    private Logger() {
        System.out.println("Logger initialized.");
    }

    // Public method to provide the global access point (thread-safe lazy initialization)
    public static Logger getInstance() {
        if (instance == null) { // Check 1: Avoid unnecessary synchronization
            synchronized (Logger.class) {
                if (instance == null) { // Check 2: Ensure only one instance is created
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void info(Class<?> classNames, String message) {
        System.out.println("[INFO] " + classNames.getName() + ": " + message);
    }

    public void debug(Class<?> classNames, String message) {
        System.out.println("[DEBUG] " + classNames.getName() + ": " + message);
    }

    public void error(Class<?> classNames, String message) {
        System.out.println("[ERROR] " + classNames.getName() + ": " + message);
    }
}

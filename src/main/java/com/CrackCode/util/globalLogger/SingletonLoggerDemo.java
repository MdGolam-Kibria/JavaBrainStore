package com.CrackCode.util.globalLogger;

public class SingletonLoggerDemo {
    public static void main(String[] args) {
        // Access the single Logger instance
        Logger logger1 = Logger.getInstance();

        // Log messages
        logger1.info(SingletonLoggerDemo.class, "Application started.");
        logger1.debug(SingletonLoggerDemo.class, "Debugging application flow.");
        logger1.error(SingletonLoggerDemo.class, "An error occurred while processing.");

        // Access the Logger instance again
        Logger logger2 = Logger.getInstance();

        // Verify that both instances are the same
        System.out.println("Are logger1 and logger2 the same instance? " + (logger1 == logger2));
    }
}

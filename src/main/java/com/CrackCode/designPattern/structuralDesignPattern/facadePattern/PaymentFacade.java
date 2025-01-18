package com.CrackCode.designPattern.structuralDesignPattern.facadePattern;

class PaymentFacade {
    private AccountChecker accountChecker;
    private PinChecker pinChecker;
    private BalanceChecker balanceChecker;
    private LoggingService loggingService;
    private NotificationService notificationService;

    public PaymentFacade() {
        this.accountChecker = new AccountChecker();
        this.pinChecker = new PinChecker();
        this.balanceChecker = new BalanceChecker();
        this.loggingService = new LoggingService();
        this.notificationService = new NotificationService();
    }

    public boolean makePayment(String cardNumber, String pin, double amount, String operationType) {
        System.out.println("Processing payment...");


        if (!accountChecker.isAccountActive(cardNumber)) {
            System.out.println("Account is inactive.");
            return false;
        }


        if (!pinChecker.isPinCorrect(cardNumber, pin)) {
            System.out.println("Invalid PIN.");
            return false;
        }


        if (!balanceChecker.hasSufficientBalance(cardNumber, amount)) {
            System.out.println("Insufficient balance.");
            return false;
        }


        loggingService.makeEntry(cardNumber, amount, operationType);

        notificationService.sendNotification(cardNumber, "Your payment of $" + amount + " has been processed.");

        System.out.println("Payment completed successfully!");
        return true;
    }
}

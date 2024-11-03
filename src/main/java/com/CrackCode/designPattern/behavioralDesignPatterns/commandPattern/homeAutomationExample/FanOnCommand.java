package com.CrackCode.designPattern.behavioralDesignPatterns.commandPattern.homeAutomationExample;

class FanOnCommand implements Command {
    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOn();
    }
}

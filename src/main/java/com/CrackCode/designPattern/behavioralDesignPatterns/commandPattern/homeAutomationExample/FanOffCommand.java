package com.CrackCode.designPattern.behavioralDesignPatterns.commandPattern.homeAutomationExample;

class FanOffCommand implements Command {
    private Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOff();
    }
}

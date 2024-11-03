package com.CrackCode.designPattern.behavioralDesignPatterns.commandPattern.homeAutomationExample;

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

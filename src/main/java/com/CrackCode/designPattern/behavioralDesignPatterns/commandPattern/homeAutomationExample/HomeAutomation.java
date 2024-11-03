package com.CrackCode.designPattern.behavioralDesignPatterns.commandPattern.homeAutomationExample;

// Command Interface
interface Command {
    void execute();
}


// Invoker Class
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Client Code
public class HomeAutomation {
    public static void main(String[] args) {
        /*
            **Key Components of the Command Pattern**<br/>
            1) Command Interface: <br/>
                This defines a common interface for all concrete command classes. It typically includes an execute() method.

            2) Concrete Command:<br/>
                These classes implement the Command interface and define the binding between a receiver and an action. Each concrete command will implement the execute() method to perform the specific action.

            3) Receiver:<br/>
                This is the object that knows how to perform the operations associated with carrying out a request. The command object calls the appropriate methods on the receiver.

            4) Invoker:<br/>
                This class is responsible for invoking the command. It holds a command and can execute it. The invoker does not need to know the details of how the command is executed.

            5) Client:<br/>
                The client creates a command object and associates it with a receiver. It then passes the command to the invoker.
         */
        // Create receivers
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        // Create commands
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        Command fanOn = new FanOnCommand(ceilingFan);
        Command fanOff = new FanOffCommand(ceilingFan);

        // Create invoker @INFO (here RemoteControl is an invoker where we register all command and triggerable event which is understandable for receiver (Light,Fan) )
        RemoteControl remote = new RemoteControl();

        // Turn on the light
        remote.setCommand(lightOn);
        remote.pressButton();

        // Turn off the light
        remote.setCommand(lightOff);
        remote.pressButton();

        // Turn on the fan
        remote.setCommand(fanOn);
        remote.pressButton();

        // Turn off the fan
        remote.setCommand(fanOff);
        remote.pressButton();
    }
}
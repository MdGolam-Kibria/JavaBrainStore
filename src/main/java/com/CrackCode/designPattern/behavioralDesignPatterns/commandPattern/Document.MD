The Command design pattern allows you to turn actions into objects, making it easier to manage, queue, and undo those actions without tightly coupling the components that perform the actions.

The Command pattern is like a remote control for actions. It makes it easy to organize, perform, and manage actions without
worrying about the details of how they work!

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



<br/>

**Real Life example about this pattern :** <br/>
Say for example you want to make a remote to automate your home Fan,Light,AC.

WORK BREAK DOWN : 
Need to create a remote where Fan,Light,AC button exists.

If someone press on the fan button than need to ON fan and again press 
    than need to OFF the FAN and this process same for Light,AC also.

For make this process automated let's make the WBS

1) Create FAN,Light first.
    ```java 
   class Fan {
        public void turnOn() {
            System.out.println("The fan is ON");
        }
    
        public void turnOff() {
            System.out.println("The fan is OFF");
        }
   }
    ```
2) Need to register command for every button. Command is common for all button that need to executable.
    So, Need to make the common Command.

    ```java 
    interface Command {
        void execute();
    }
    ```
3) Now adjust the command for every device button in our case  FAN,Light

FAN : 

```java
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
 ```


```java
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
 ```

LIGHT : 

```java
// Concrete Command Classes
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}
 ```


```java
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
 ```

4) Now create the remote to register the above created command.

```java
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
```
Register command to the remote and make operation : 

```java
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
```





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








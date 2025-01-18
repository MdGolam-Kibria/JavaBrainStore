What is the Facade Design Pattern?
The Facade Design Pattern is a structural design pattern that provides a simplified interface to a 
larger and more complex system of classes, libraries, or frameworks. It hides the complexity of the subsystems 
and exposes only the essential functionality that the client needs.

The goal of the facade is to:

1) Simplify the client’s interaction with a complex system.
2) Decouple the client from the subsystems.
3) Improve readability and reduce dependencies in the code.

**Key Features of the Facade Pattern:**

1) Simplified Interface: The client uses a single class (the facade) to perform complex operations without needing to understand the details of subsystems.
2) Encapsulation of Complexity: All the intricate details of the subsystems are hidden behind the facade.
3) Decoupling: The client does not need to know about the existence of subsystems, reducing dependencies.
**Real-Life Example:** Restaurant Ordering System

_Imagine you go to a restaurant to have dinner. As a customer, you:_

i) Place an order with a waiter.
ii) Receive your meal after some time.

You do not need to deal with the complexities of the kitchen:

The waiter (the facade) takes your order.
The waiter communicates with various subsystems (kitchen, billing, cleaning staff, etc.).
You, the customer, only interact with the waiter.
Subsystems in the Restaurant:
Chef: Prepares the food.
Billing System: Generates the bill.
Cleaning Staff: Cleans the table.
Serving Staff: Serves the food.
The Waiter is the Facade that hides the complexity of these operations and provides a simple interface for customers.
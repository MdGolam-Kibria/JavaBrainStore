# In-Depth Exploration of the Java Virtual Machine (JVM) Internals (English)



# In-Depth Exploration of the Java Virtual Machine (JVM) Internals

## Introduction

The Java Virtual Machine (JVM) stands as the cornerstone of the Java platform, serving as the runtime engine that executes Java applications. It is the entity responsible for invoking the `main` method within Java code, initiating the program's execution. As an integral component of the Java Runtime Environment (JRE), the JVM embodies the principle of "Write Once, Run Anywhere" (WORA). This powerful concept allows developers to write Java code on a single system with the confidence that it can execute seamlessly on any other system equipped with a compatible JVM, eliminating the need for platform-specific adjustments. This cross-platform compatibility is achieved through the JVM's sophisticated architecture, which abstracts away the underlying hardware and operating system details. The journey from source code to execution begins when a Java compiler transforms human-readable `.java` files into platform-independent `.class` files. These files contain bytecode, a set of instructions specifically designed for the JVM. When a Java program is run, the JVM takes these `.class` files and processes them through a series of well-defined steps, collectively constituting the JVM's internal workings.

## The Classloader Subsystem

The Classloader Subsystem is the gateway for Java code execution, tasked with the crucial responsibilities of dynamically loading, linking, and initializing classes and interfaces. This process ensures that Java classes are brought into the JVM only when they are needed, contributing to the platform's flexibility and efficiency. The subsystem performs its duties in three distinct phases:

### 1. Loading

The loading phase begins when the Classloader reads a `.class` file. It parses the binary data contained within the file, extracting essential information about the class or interface. This information, including the fully qualified name of the class, its immediate parent class, whether it represents a class, interface, or enum, and details about its modifiers, variables, and methods, is then stored in a dedicated memory region within the JVM called the Method Area. For every `.class` file successfully loaded, the JVM also creates an instance of the `java.lang.Class` object in the Heap memory. This `Class` object serves as a runtime representation of the loaded class, providing programmatic access to its metadata, such as its name, parent, fields, and methods, often utilized through reflection via the `getClass()` method inherited from `java.lang.Object`. It's important to note that the JVM ensures only one `Class` object is created per loaded class, regardless of how many times it might be referenced or instantiated.

There are typically three main classloaders involved in this process, operating under a delegation hierarchy:

*   **Bootstrap Class Loader:** This is the primordial classloader, intrinsic to every JVM implementation. It is responsible for loading the core Java API classes residing in the `JAVA_HOME/lib` directory (often referred to as the bootstrap path). Due to its fundamental role and need for close interaction with the native operating system, it is usually implemented in native code (like C or C++) rather than Java itself.
*   **Extension Class Loader:** Functioning as a child of the Bootstrap Class Loader, this loader handles classes located in the Java extensions directory, typically `JAVA_HOME/jre/lib/ext`, or other directories specified by the `java.ext.dirs` system property. It is implemented in Java, specifically by the `sun.misc.Launcher$ExtClassLoader` class in many standard JVM implementations.
*   **System/Application Class Loader:** As a child of the Extension Class Loader, this is the final loader in the standard hierarchy. Its primary task is to load the application-specific classes found on the application classpath, usually defined by the `CLASSPATH` environment variable or the `-cp` command-line option. Like the Extension Class Loader, it is implemented in Java, commonly by the `sun.misc.Launcher$AppClassLoader` class.

The JVM employs a **Delegation-Hierarchy Principle** for loading classes. When a request to load a class is made (e.g., to the System Class Loader), it first delegates the request up the hierarchy to its parent (Extension Class Loader). The Extension Class Loader, in turn, delegates to its parent (Bootstrap Class Loader). The Bootstrap Class Loader attempts to load the class first. If it finds and loads the class (because it's a core API class), the process stops. If not, the request flows back down to the Extension Class Loader, which attempts to load it from the extension directories. If it fails, the request finally returns to the System/Application Class Loader, which searches the application classpath. If the class cannot be located even after searching all these locations, the JVM throws a `java.lang.ClassNotFoundException`.

### 2. Linking

Following successful loading, the class undergoes the linking phase, which involves integrating the newly loaded type into the runtime state of the JVM. Linking itself comprises three sub-steps:

*   **Verification:** This is a critical security step where the JVM ensures the loaded binary representation of the class is structurally correct, adheres to the JVM specification, and was generated by a valid compiler. The Bytecode Verifier component performs checks like ensuring proper formatting, valid instructions, correct operand stack usage, and adherence to access control rules. If verification fails due to malformed or malicious bytecode, a `java.lang.VerifyError` is thrown, preventing potentially harmful code from executing.
*   **Preparation:** In this step, the JVM allocates memory for the class's static variables (also known as class variables) and initializes them with their default values according to their type (e.g., 0 for numeric types, `false` for boolean, `null` for reference types). Note that this is just default initialization; the actual initial values defined in the source code are assigned during the Initialization phase.
*   **Resolution (Optional):** Resolution is the process of transforming symbolic references within the class's runtime constant pool into direct references. Symbolic references are logical references used in the bytecode (e.g., the name of a class or method), while direct references are typically memory addresses or offsets that allow the JVM to directly access the referenced entity. This step involves searching the Method Area to locate the referenced classes, fields, and methods. While the JVM specification allows resolution to happen at this stage, many implementations perform it lazily, resolving symbolic references only when they are actually used for the first time during execution.

### 3. Initialization

The final phase of the Classloader Subsystem is initialization. During this stage, the JVM executes the class's initialization logic. This involves assigning the actual initial values (as defined in the source code) to static variables and executing any static initialization blocks (`static {}` blocks). The initialization process occurs in a specific order: top-to-bottom within the class definition, and crucially, the superclass is initialized before its subclass. Initialization is triggered only when a class is actively used for the first time, such as when an instance is created, a static method is invoked, or a static field is accessed (unless it's a compile-time constant). The JVM ensures that initialization is thread-safe, meaning a class is initialized only once, even in multithreaded environments.




## JVM Runtime Data Areas

Once classes are loaded, linked, and initialized, the JVM needs specific memory regions to manage the execution of the program. These regions, collectively known as the Runtime Data Areas, are defined by the JVM specification and are essential for storing data during program execution. Some of these areas are created when the JVM starts and destroyed only when it exits, while others are created and destroyed on a per-thread basis. These areas are crucial for understanding how Java manages memory and executes code.

### 1. Method Area

The Method Area is a fundamental memory region shared among all threads within the JVM. It is created upon JVM startup and primarily serves as storage for per-class structures. This includes the runtime constant pool (containing constants and symbolic references), field and method data (information about variables and methods within a class), and the actual bytecode for methods and constructors, including special methods like class initializers (`<clinit>`). Static variables associated with classes are also stored here. Although the Method Area is logically considered part of the heap, the JVM specification doesn't mandate a specific location or management policy. Implementations might choose different strategies, such as not performing garbage collection or compaction within this area, or placing it outside the main garbage-collected heap. The size of the Method Area can be fixed or dynamically adjusted based on the application's needs. If the JVM cannot allocate sufficient memory within the Method Area to satisfy a request (e.g., loading a new class or creating its runtime constant pool), it will throw an `OutOfMemoryError`.

### 2. Heap Area

The Heap Area is perhaps the most well-known runtime data area, also shared among all JVM threads and created at JVM startup. This is the primary region from which memory is allocated for all class instances (objects) and arrays during program execution. Whenever you use the `new` keyword to create an object or instantiate an array, the memory for that entity is allocated on the heap. The management of the heap is typically handled by an automatic storage management system, commonly known as the Garbage Collector (GC). The GC's role is to automatically identify and reclaim memory occupied by objects that are no longer referenced by the running application, preventing memory leaks and simplifying memory management for developers. Similar to the Method Area, the heap's size can be fixed or dynamically managed (expanded or contracted) by the JVM implementation. The memory within the heap does not need to be contiguous. If an application attempts to allocate memory on the heap (e.g., creating a large object or array) and the required space cannot be made available, even after garbage collection attempts, the JVM will throw an `OutOfMemoryError`.

### 3. Stack Area (JVM Stacks)

Unlike the Method Area and Heap, JVM Stacks are private to each thread. A new JVM Stack is created the moment a thread starts its execution. The stack's primary purpose is to store frames. Each frame corresponds to a single method invocation. When a method is called, a new frame is pushed onto the stack associated with the executing thread; when the method completes (either by returning normally or throwing an unhandled exception), its corresponding frame is popped off the stack. Each frame contains the method's local variables, an operand stack for intermediate calculations, and data to support dynamic linking and method return values. JVM Stacks are analogous to the call stacks found in conventional languages like C or C++. The memory for frames can be allocated from the stack itself, or potentially from the heap in some implementations, and the stack memory does not need to be contiguous. JVM Stacks can have a fixed size or be dynamically expanded as needed during computation. However, there are limits. If a thread's computation requires a larger stack than permitted (either due to deep recursion or large frames), a `StackOverflowError` is thrown. If the stack is configured for dynamic expansion and an expansion is attempted but fails due to insufficient memory, an `OutOfMemoryError` is thrown.

### 4. PC Registers (Program Counter Registers)

Each thread within the JVM also has its own Program Counter (PC) Register. The PC Register is relatively small and holds the address of the Java Virtual Machine instruction currently being executed by that thread. Since Java methods can be executed concurrently by multiple threads, each thread needs its own PC register to keep track of its independent execution point. If the method currently being executed by the thread is a `native` method (i.e., implemented in a language other than Java, like C or C++), the value of the PC Register is undefined, as the execution is outside the direct control of the JVM's instruction set. The PC Register is typically wide enough to store a `returnAddress` or a native pointer on the specific platform.

### 5. Native Method Stacks

In addition to the JVM Stacks used for executing Java bytecode, the JVM often utilizes separate Native Method Stacks to support the execution of `native` methods. Similar to JVM Stacks, a Native Method Stack is typically allocated per thread. When a thread invokes a native method, it often transitions from the JVM Stack to a corresponding Native Method Stack, which operates according to the conventions of the native language (often resembling a C stack). These stacks store the state necessary for native method execution. Like JVM Stacks, Native Method Stacks can be of fixed size or dynamically expandable. If a thread requires more space in its native method stack than is available, a `StackOverflowError` (or a similar platform-specific error) might be thrown. If dynamic expansion is attempted but fails due to lack of memory, an `OutOfMemoryError` occurs.

*(Sources: Based on information from GeeksforGeeks JVM Architecture article and Oracle Java SE 7 JVM Specification, Chapter 2)*




## Execution Engine

The Execution Engine is the heart of the JVM, responsible for actually executing the bytecode contained in the loaded `.class` files. It reads the bytecode instructions one by one, accesses data stored in the various Runtime Data Areas (like the Heap and Stacks), and performs the computations defined by the code. The Execution Engine is a complex component, and its internal workings significantly impact the performance of Java applications. Key parts of the Execution Engine include the Interpreter, the Just-In-Time (JIT) Compiler, and the Garbage Collector (GC).

### 1. Interpreter

The simplest way to execute bytecode is through interpretation. The Interpreter reads the bytecode stream instruction by instruction, deciphers the meaning of each instruction, and immediately executes the corresponding operation. This process is straightforward but can be relatively slow, especially for code sections that are executed repeatedly, such as methods within loops. Every time a frequently called method is invoked, the interpreter has to go through the process of reading and interpreting its bytecode again, leading to performance overhead.

### 2. Just-In-Time (JIT) Compiler

To overcome the performance limitations of pure interpretation, modern JVMs employ a Just-In-Time (JIT) Compiler. The JIT Compiler works alongside the Interpreter to improve performance. Its primary goal is to identify "hotspots" – sections of bytecode (typically methods or loops) that are executed frequently. Instead of interpreting these hotspots repeatedly, the JIT Compiler translates them directly into native machine code, optimized for the underlying hardware architecture. This compiled native code can then be executed directly by the host CPU, resulting in significantly faster performance compared to interpretation. The JVM often uses profiling techniques to monitor code execution and decide which parts are worth compiling. Once a method is compiled, subsequent calls to it will execute the faster native version instead of being interpreted. This adaptive optimization strategy allows Java applications to achieve performance levels close to those of natively compiled languages like C++.

### 3. Garbage Collector (GC)



## JVM Architecture Diagram

![JVM Architecture](https://private-us-east-1.manuscdn.com/sessionFile/BOy9L34a5bRR8R5Jyt2euh/sandbox/FxyuV7K7bzHzUzTbgMj6OK-images_1749023060057_na1fn_L2hvbWUvdWJ1bnR1L2p2bV9yZXNlYXJjaC9qdm1fYXJjaGl0ZWN0dXJlX2RpYWdyYW0.png?Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9wcml2YXRlLXVzLWVhc3QtMS5tYW51c2Nkbi5jb20vc2Vzc2lvbkZpbGUvQk95OUwzNGE1YlJSOFI1Snl0MmV1aC9zYW5kYm94L0Z4eXVWN0s3YnpIelV6VGJnTWo2T0staW1hZ2VzXzE3NDkwMjMwNjAwNTdfbmExZm5fTDJodmJXVXZkV0oxYm5SMUwycDJiVjl5WlhObFlYSmphQzlxZG0xZllYSmphR2wwWldOMGRYSmxYMlJwWVdkeVlXMC5wbmciLCJDb25kaXRpb24iOnsiRGF0ZUxlc3NUaGFuIjp7IkFXUzpFcG9jaFRpbWUiOjE3NjcyMjU2MDB9fX1dfQ__&Key-Pair-Id=K2HSFNDJXOU9YS&Signature=Un~HHTDI8nvkuvIMSxNdqs3nIvuOPRG1mM8mFFA6JMKSpEPoAF-wmDXu826ZvsuwAinGnanJTtrj5KXrdGNWTYc7rRy1hoM-bNYYixwUJR9y8gOL0IA8qetc1h0c763gYFSuvy~pNym9OzjYOguBKOUU6SkP-8udX509qBziszZweyci5GndL9qCgwPQK-z3Ckn8ShTrKUq1d2FVEEKjCx9nd7wcwqrt660oIPbWK81sNBvyxL0G8MBmLFI7zpInGdapjCZLk8DPC-GLc3iTCflzotcJUVBQymJRcED02IIcSMV6cXEHbX4QT0AaIDgKuWDcB5KSR7rYGaWmycGdDQ__)

*(Note: This diagram provides a high-level overview. Specific implementations may vary.)*



# JVM Internals: Code Examples

This section provides simple Java code examples to illustrate some of the concepts discussed regarding JVM internals.

## Example 1: Object Creation and Heap Memory

**Concept:** When the `new` keyword is used, memory for the object is allocated on the Heap. Each object instance resides in the Heap area, which is shared among all threads.

**English Explanation:**
The following code creates two instances of a simple `Dog` class. Each time `new Dog(...)` is called, the JVM allocates memory on the Heap to store the object's instance variables (`name` and `breed`). The variables `dog1` and `dog2` are references stored likely on the stack (within the `main` method's frame), pointing to their respective objects on the Heap.

**Bangla Explanation (বাংলা ব্যাখ্যা):**
নিম্নলিখিত কোডটি একটি সাধারণ `Dog` ক্লাসের দুটি ইনস্ট্যান্স তৈরি করে। প্রতিবার `new Dog(...)` কল করা হলে, JVM অবজেক্টের ইনস্ট্যান্স ভেরিয়েবল (`name` এবং `breed`) সংরক্ষণ করার জন্য হিপে মেমরি বরাদ্দ করে। `dog1` এবং `dog2` ভেরিয়েবলগুলি হল রেফারেন্স যা সম্ভবত স্ট্যাকে সংরক্ষিত থাকে (`main` মেথডের ফ্রেমের মধ্যে), যা হিপে তাদের নিজ নিজ অবজেক্টের দিকে নির্দেশ করে।

**Code (Java):**
```java
class Dog {
    String name;
    String breed;

    public Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Breed: " + breed + " (Object Hash: " + this.hashCode() + ")");
    }
}

public class HeapExample {
    public static void main(String[] args) {
        // Create first Dog object on the Heap
        Dog dog1 = new Dog("Buddy", "Golden Retriever");

        // Create second Dog object on the Heap
        Dog dog2 = new Dog("Lucy", "Labrador");

        System.out.println("Created two Dog objects on the Heap:");
        dog1.displayInfo();
        dog2.displayInfo();

        // dog1 and dog2 are references pointing to different objects
        System.out.println("Are dog1 and dog2 the same object? " + (dog1 == dog2)); 
    }
}
```




## Example 2: Method Calls and Stack Frames

**Concept:** Each time a method is called, a new frame is pushed onto the calling thread's JVM Stack. This frame contains local variables and parameters for that specific method call. When the method returns, its frame is popped off the stack.

**English Explanation:**
In this example, `main` calls `methodA`, and `methodA` calls `methodB`. Each call creates a new stack frame.
1.  `main` starts: A frame for `main` is pushed onto the stack.
2.  `methodA` is called: A new frame for `methodA` (with parameter `x`) is pushed on top of `main`'s frame.
3.  `methodB` is called: A new frame for `methodB` (with parameter `y`) is pushed on top of `methodA`'s frame.
4.  `methodB` finishes: `methodB`'s frame is popped.
5.  `methodA` finishes: `methodA`'s frame is popped.
6.  `main` finishes: `main`'s frame is popped. The stack becomes empty (for this thread).
Local variables (`a` in `main`, `localVarA` in `methodA`, `localVarB` in `methodB`) exist only within their respective frames.

**Bangla Explanation (বাংলা ব্যাখ্যা):**
এই উদাহরণে, `main` মেথড `methodA` কে কল করে, এবং `methodA` মেথড `methodB` কে কল করে। প্রতিটি কল একটি নতুন স্ট্যাক ফ্রেম তৈরি করে।
১. `main` শুরু হয়: `main` এর জন্য একটি ফ্রেম স্ট্যাকের উপর পুশ করা হয়।
২. `methodA` কল করা হয়: `methodA` এর জন্য একটি নতুন ফ্রেম (`x` প্যারামিটার সহ) `main` এর ফ্রেমের উপরে পুশ করা হয়।
৩. `methodB` কল করা হয়: `methodB` এর জন্য একটি নতুন ফ্রেম (`y` প্যারামিটার সহ) `methodA` এর ফ্রেমের উপরে পুশ করা হয়।
৪. `methodB` শেষ হয়: `methodB` এর ফ্রেম পপ করা হয়।
৫. `methodA` শেষ হয়: `methodA` এর ফ্রেম পপ করা হয়।
৬. `main` শেষ হয়: `main` এর ফ্রেম পপ করা হয়। স্ট্যাক খালি হয়ে যায় (এই থ্রেডের জন্য)।
লোকাল ভেরিয়েবল (`main` এ `a`, `methodA` তে `localVarA`, `methodB` তে `localVarB`) শুধুমাত্র তাদের নিজ নিজ ফ্রেমের মধ্যে বিদ্যমান থাকে।

**Code (Java):**
```java
public class StackFrameExample {

    public static void main(String[] args) {
        System.out.println("Entering main method");
        int a = 10;
        methodA(a);
        System.out.println("Exiting main method");
    }

    public static void methodA(int x) {
        System.out.println("Entering methodA, received: " + x);
        int localVarA = x * 2;
        methodB(localVarA);
        System.out.println("Exiting methodA");
    }

    public static void methodB(int y) {
        System.out.println("Entering methodB, received: " + y);
        int localVarB = y + 5;
        System.out.println("Result in methodB: " + localVarB);
        System.out.println("Exiting methodB");
    }
}
```

## Example 3: Hinting at Garbage Collection

**Concept:** Objects on the Heap that are no longer referenced by any part of the running program become eligible for Garbage Collection (GC). Setting a reference variable to `null` removes one reference to the object.

**English Explanation:**
This code creates a `Dog` object. Initially, `myDog` references this object. When `myDog` is set to `null`, the `Dog` object created earlier might become unreachable (assuming no other references exist). The `System.gc()` call is a *suggestion* to the JVM to run the garbage collector, but it's not guaranteed to run immediately or at all. If the GC runs and finds the `Dog` object unreachable, it will reclaim its memory on the Heap.

**Bangla Explanation (বাংলা ব্যাখ্যা):**
এই কোডটি একটি `Dog` অবজেক্ট তৈরি করে। প্রাথমিকভাবে, `myDog` এই অবজেক্টটিকে রেফারেন্স করে। যখন `myDog` কে `null` সেট করা হয়, তখন আগে তৈরি করা `Dog` অবজেক্টটি অপ্রাপ্য (unreachable) হয়ে যেতে পারে (যদি অন্য কোনো রেফারেন্স না থাকে)। `System.gc()` কলটি গার্বেজ কালেক্টর চালানোর জন্য JVM-কে একটি *পরামর্শ* মাত্র, কিন্তু এটি অবিলম্বে বা আদৌ চলবে কিনা তার কোনো নিশ্চয়তা নেই। যদি GC চলে এবং `Dog` অবজেক্টটিকে অপ্রাপ্য খুঁজে পায়, তবে এটি হিপে তার মেমরি পুনরুদ্ধার করবে।

**Code (Java):**
```java
// Reusing the Dog class from HeapExample

public class GCExample {
    public static void main(String[] args) {
        System.out.println("Creating a Dog object...");
        Dog myDog = new Dog("Max", "German Shepherd");
        myDog.displayInfo();

        System.out.println("Setting reference to null...");
        // Remove the reference to the object
        myDog = null; 

        System.out.println("Suggesting Garbage Collection...");
        // Suggest JVM to run Garbage Collector
        // Note: This is only a suggestion, GC is not guaranteed to run.
        System.gc(); 

        System.out.println("Program finished. The Dog object might have been garbage collected if unreachable.");
        // We cannot deterministically check if GC ran or collected the object here.
    }
}
```

## Example 4: Class Loading

**Concept:** Classes are loaded by the Classloader Subsystem, often lazily when first needed. We can also trigger loading explicitly.

**English Explanation:**
The `Class.forName("java.util.ArrayList")` call explicitly requests the JVM to load the `java.util.ArrayList` class if it hasn't been loaded already. This involves the Classloader Subsystem finding the `.class` file, loading its bytecode, verifying it, preparing memory, and potentially initializing it (though `forName` alone doesn't guarantee initialization without a second argument set to true). The output confirms that the `Class` object representing `ArrayList` was successfully obtained.

**Bangla Explanation (বাংলা ব্যাখ্যা):**
`Class.forName("java.util.ArrayList")` কলটি JVM-কে স্পষ্টভাবে `java.util.ArrayList` ক্লাসটি লোড করার জন্য অনুরোধ করে, যদি এটি ইতিমধ্যে লোড না হয়ে থাকে। এর মধ্যে ক্লাসলোডার সাবসিস্টেম `.class` ফাইল খুঁজে বের করা, তার বাইটকোড লোড করা, যাচাই করা, মেমরি প্রস্তুত করা এবং সম্ভাব্যভাবে ইনিশিয়ালাইজ করা জড়িত থাকে (যদিও `forName` একা ইনিশিয়ালাইজেশনের নিশ্চয়তা দেয় না যদি দ্বিতীয় আর্গুমেন্ট true সেট করা না হয়)। আউটপুট নিশ্চিত করে যে `ArrayList` প্রতিনিধিত্বকারী `Class` অবজেক্টটি সফলভাবে প্রাপ্ত হয়েছে।

**Code (Java):**
```java
public class ClassLoadingExample {
    public static void main(String[] args) {
        System.out.println("Attempting to load java.util.ArrayList...");
        try {
            // Explicitly load the ArrayList class
            Class<?> arrayListClass = Class.forName("java.util.ArrayList");
            System.out.println("Successfully loaded class: " + arrayListClass.getName());
            System.out.println("Loaded by ClassLoader: " + arrayListClass.getClassLoader());

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Class not found - " + e.getMessage());
        }
        System.out.println("Program finished.");
    }
}
```



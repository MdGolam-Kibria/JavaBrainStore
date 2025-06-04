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


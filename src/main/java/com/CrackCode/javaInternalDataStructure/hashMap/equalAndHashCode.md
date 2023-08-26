# <H1>  `equals()` and `hashcode()` method explanation </h1><br/>
 
## ****About equals() : <br/>****

In Java, the equals method is used to compare two objects for equality. By default, the equals method in
the Object class (which is the root class for all Java objects) compares objects based on their memory
addresses, essentially checking if they are the same instance in memory.

Example :

_Here's how the equals method works when you don't override it in your class:_

* **Default Behavior:** If you don't provide your own implementation of the equals method in your class,
it inherits the default implementation from the Object class.
* **Default Comparison:** The default implementation of equals uses the` ==` operator to compare the
memory addresses of the objects. In other words, it checks if the two object references point to the exact same memory location.


## **`equal()` have 2 types of implementation :**
_**1) Default Implementation.**_

_**2) Custom Implementation.**_


## (1) Default Implementation of `equal()`  (JAVA 8) :
```java
public class Object {
    public boolean equals(Object obj) {
        return (this == obj);
    }
}
```


```java
public class DefaultEqualMethodTest{
    public static void main(String[] args) {
        Person obj1 = new Person();
        obj1.setName("Golam Kibria");
        obj1.setAge(27);
        Person obj2 = obj1; // obj2 now refers to the same object as obj1
        obj2.setName("Golam Rabbani");
        obj2.setAge(30);
        boolean result = obj1.equals(obj2);
    }
}
```
In this example, result will be true because obj1 and obj2 point to the same object in memory.


**Custom implementation of  `equal()` :** 

## (1) Custom Implementation of `equal()`  (JAVA 8) :

If you want to customize the behavior of the equals method to compare objects based on their content or attributes,
you should override it in your class. When you override the equals method, you typically compare the 
attributes (state) of the objects to determine if they are equal.


**Here's an example of how you might override the equals method in a class:**

```java
public class Person {
    private String name;
    private int age;

    // Constructors, getters, setters, etc.

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            /*
              If the two objects are the same instance 
              (contains from same memory address cause `==` always check memory address for this case), they are equal
             */
            return true; 
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // If the objects are of different classes or obj is null, they are not equal
        }
        Person person = (Person) obj; // Cast obj to the class type

        // Compare the attributes of the objects to determine equality
        return age == person.age && Objects.equals(name, person.name);
    }
}
```

In this custom equals method, we first check if the two objects are the same instance using `this == obj`. 
If they are (contains from same memory address cause `==` always check memory address for this case), 
they are considered equal.

Next, we check if obj is null or if it's not an instance of `Person`, in which case we return false.

Finally, if obj is an instance of `Person`, we cast it to `Person` and then 
compare the attributes (age and name) to determine if the two objects are equal based on your custom criteria.
We use `Objects.equals()` for comparing the name attribute because it handles null values gracefully.

## ****About hashcode() : <br/>****

By default, the` hashCode()` method in the Object class generates hash codes based on the memory address 
of the object. In other words, if you don't override the hashCode() method in your custom class,
each object will have a unique hash code, even if they have the same attribute values.




## **`hashcode()` have 2 types of implementation :** 
_**1) Default Implementation.**_

_**2) Custom Implementation.**_


## (1) Default Implementation of `hashcode()` (JAVA 8):

If wanna give an example with a custom-made class called `person` have some property as shared below, <br/>

```JAVA
@Data
public class Person {
    private int id;
    private String name;
    private String email;
    private String phone;
    private List<String> addresses;
    private Integer age;
}
```
As we know every class parent class is Object. every class have some default overrridable method as like `hashcode()` `equals()` `toString()` `clone()` `finalize()`.
in this context we just discuss about `hashcode()` here.


We are going to discuss these`hashcode()` method by providing example with the above **`Person.class`** 

##  `hashcode()` Method :

If we don't override the `hashcode()` the  method default implementation and the actual scenario with the `Person.class` are shared below,

**`Person` class implementation :** 

```java
@Data
public class Person {
    private int id;
    private String name;
    private String email;
    private String phone;
    private List<String> addresses;
    private Integer age;
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
```

**Parent class of the `Person` object `hashCode()` method actual implementation** _(JAVA 8 CODE)_**:**

```java
public class Object {
    /**
     * @INFO
     * the native keyword in public native int hashCode(); indicates that the hashCode method's 
     * implementation is provided by the JVM and is typically based on the memory address of the object. 
     * You can override this method in your own classes to define custom hash code generation based on 
     * your class's attributes.
     */
    public native int hashCode();
}
```

```java
public class EqualHashCodeMethodTest {
    public static void main(String[] args) {
        Map<Person, Person> map = new HashMap<>();
        Person p = new Person();
        p.setName("kibria");
        map.put(p, p);
        p.setAge(27);
        Person person = map.get(p);
        System.out.println("After put person object to map the person name and age is : " + person.getName() + "=>" + person.getAge());
        p.setAge(12);
        System.out.println("After put to map the changed the age and the changed name and age : " + map.get(p).getName() + "=>" + map.get(p).getAge());
        /*
           <h1>Output : 
           After put person object to map the person name and age is : kibria=>27
           After put to map the changed the age and the changed name and age : kibria=>12
         */

    }
}
```

##### Explanation : 
On above `EqualHashCodeMethodTest.class` inside `main()` method have created a `HashMap<>()` object called `map` 
with defined key and value `Person`.

After that we create a `Person` object with set the person name and put the person object to map as key and value.

After put the person object to map then we set the person object age.

Now wanna get the person object from the map which object set previously.

Now print the received the person object `person` name and age and again changed the person object `p` age and print the changes person object name and age.

Based on our `EqualHashCodeMethodTest .class` code snips we have created only one Person
object.
As we use `new HashMap<>();`. we know `new HashMap<>();` put data to a bucket as `Node` `new HashMap<>();`
internally maintain `LinkedList` . Every bucket's holds Node.
Node architecture are shared below, 

```java
static class Node<K, V> implements Map.Entry<K, V> {
    final int hash;
    final K key;
    V value;
    Node<K, V> next;

    Node(int hash, K key, V value, Node<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
```
So, when we put item to a `HashMap<>()` it store value to a bucket by reference 
the provided key hash value.
For more details about `HashMap<>()` <button>[click here.](HashMapInternalStructure.md)</button>


So, we put person object to hashmap it put the person data to bucket by the provided key `hashcode()`.
as per our `EqualHashCodeMethodTest.class` code snips the `Person.class` have default `hashcode()` that only calculate the 
person class memory reference. So, after put data to `HashMap<>()` if we changed the person object property value
it will stay with same memory reference. that's why we are able to get the the person object from `HashMap<>()` because 
the person object `hashCode()` is same as `HashMap<>()` holds data to bucket by **hashCode** so if the person object
reference is same we are defensively able to get data from `HashMap<>()` because the default `hashCode()` method of the 
person class only return the person object memory referance without comparing the person object property's values.


## (1) Custom Implementation of `hashcode()` (JAVA 8):

It's often a good practice to override the hashCode() method in your custom classes if you override 
the equals() method. When you override hashCode(), you should compute the hash code based on the attributes 
that are used to determine equality. This ensures that objects with the same attribute values have
the same hash code.

For explain about the `hashCode()` custom implementation we need to override the  `hashcode()` method with custom logic.

In this custom case we use the same `Person.class` and `EqualHashCodeMethodTest.class` by change a little bit in `Person.class`
`hashCode()`method as like below,


```java
@Data
public class Person {
    private int id;
    private String name;
    private String email;
    private String phone;
    private List<String> addresses;
    private Integer age;
    
/*    @Override
    public int hashCode() {//default implementation
        return super.hashCode();
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
                getId(),
                getName(), 
                getEmail(),
                getPhone(), 
                getAddresses(), 
                getAge());
    }
}
```
`HashMap<>()` internally has a `hash()` function. When you put data into the HashMap, this `hash()` function is called. It takes the provided key ` hashCode()`
and calculates a bucket position using an algorithm. 
Data is stored in the selected bucket based on the calculated `hash`.

In our code, we have put data into the `HashMap<>()` and then later changed the age property of the `Person`
object and attempted to retrieve the value using `map.get(p)`. However, this get operation returns `null`. 

**Here's why:**

When you call `map.get(p)`, the get method calls `p.hashCode()` from within the `HashMap<>()`  `hash()` function.

The `p.hashCode()` call goes to the `hashCode()` method of the Person class. The Person class  `hashCode()` 
function generates a `hashCode` based on the object's **memory reference** and all
the values of the `Person` class properties.

**Here's the issue:** 

When you initially put data into the `HashMap<>()` with a Person object that didn't have an `age` property set. 
Later, you added an age value to the same `Person` object. When you called `map.get(p)`,
it internally called` p.hashCode() `again, but now the `Person` object has a different `hashCode`
due to the added age.

This difference in `hashCodes` is problematic because when the `HashMap<>()` looks for the previously stored object,
it uses the new, different `hashCode` to search. Since the old `hashCode` doesn't match any stored keys, 
the `HashMap<>()` doesn't find any value, resulting in `null` being returned.

The core issue here is that the hash code of an object should remain consistent throughout its lifetime 
if the object's properties used in the `hashCode()` calculation remain unchanged. 
When properties change, and those properties are part of the hash code calculation, 
you might encounter difficulties retrieving objects from collections like `HashMap<>()` 
unless you remove and reinsert the object with the updated properties.
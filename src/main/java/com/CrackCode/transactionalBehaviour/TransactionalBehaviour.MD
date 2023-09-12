# About `@Transactional` with its Behaviour <br/>

In Spring Boot, you can use the @Transactional annotation to set different isolation levels for your database transactions. Spring Boot provides support for various transaction isolation levels, and you can specify them using the isolation attribute of the @Transactional annotation.

Here are the different isolation levels you can use with @Transactional in Spring Boot:

DEFAULT (Isolation.DEFAULT): This is the default isolation level used by your database system. It typically corresponds to the default isolation level of your database management system. It's a good choice if you want to rely on the default behavior.

READ_UNCOMMITTED (Isolation.READ_UNCOMMITTED): This is the lowest isolation level, allowing transactions to read uncommitted changes made by other transactions. It may lead to dirty reads, non-repeatable reads, and phantom reads.

READ_COMMITTED (Isolation.READ_COMMITTED): This is the default isolation level for most databases. It ensures that a transaction can only read committed changes made by other transactions, avoiding dirty reads.

REPEATABLE_READ (Isolation.REPEATABLE_READ): This isolation level prevents non-repeatable reads, meaning that once a transaction reads a value, it will always see the same value, even if other transactions modify it. However, it may still allow phantom reads.

SERIALIZABLE (Isolation.SERIALIZABLE): This is the highest isolation level, providing the strongest guarantee of data consistency. It ensures that no other transactions can access the data being modified by a transaction until it's completed. This level avoids dirty reads, non-repeatable reads, and phantom reads but can be less efficient due to increased locking.

To use one of these isolation levels with the @Transactional annotation in your Spring Boot application, you can specify it like this:
```java
@Transactional(isolation = Isolation.READ_COMMITTED)
public void someTransactionalMethod() {
// Your transactional code here
}
```
You can replace Isolation.READ_COMMITTED with the desired isolation level based on your application's requirements.
Keep in mind that the actual behavior may vary depending on your database system, 
so it's essential to test and verify the isolation level's behavior with your specific database.


### For SERIALIZABLE (Isolation.SERIALIZABLE) if other transaction wanna access data then what will happen ?

**Answer:** 

In the SERIALIZABLE isolation level, transactions are completely isolated from each other, meaning that no other transactions can access the data being modified by a transaction until it's completed. If another transaction attempts to access the same data while a transaction is running with SERIALIZABLE isolation, it will be blocked and forced to wait until the first transaction is finished.

Here's what happens when another transaction tries to access data that is already locked by a transaction with SERIALIZABLE isolation:

Transaction A begins and acquires a lock on a specific set of data.

Transaction B attempts to access the same data while Transaction A is still in progress.

Since SERIALIZABLE is the highest isolation level, Transaction B is blocked and cannot proceed until Transaction A is completed and releases its lock on the data.

Once Transaction A commits or rolls back, Transaction B is allowed to proceed and access the data.

This behavior ensures that there are no concurrent modifications or reads on the same data during a transaction with SERIALIZABLE isolation. While this level of isolation provides strong data consistency guarantees, it can also lead to potential performance issues, as transactions may be blocked waiting for locks to be released. Therefore, it should be used judiciously and only when necessary for critical parts of your application where data consistency is paramount.
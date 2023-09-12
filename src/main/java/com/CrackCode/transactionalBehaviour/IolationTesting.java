package com.CrackCode.transactionalBehaviour;

import com.CrackCode.entity.Person;
import com.CrackCode.repository.PersonRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IolationTesting extends Helper {
    private final PersonRepository personRepository;

    public IolationTesting(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/getPersonWithDefaultIsolation")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public boolean getPersonWithDefaultIsolation() {
        /**
         * This is the default isolation level used by your database system.
         * It typically corresponds to the default isolation level of your database management system.
         * It's a good choice if you want to rely on the default behavior.
         * For Isolation.DEFAULT the Isolation depend on database system as like shared below,
         * For MySql   : the Isolation.DEFAULT = Isolation.REPEATABLE_READ
         * For Oracle  : the Isolation.DEFAULT = Isolation.READ_COMMITTED
         */
        getPersonWithDefaultIsolation(personRepository);
        return true;
    }

    @GetMapping("/getPersonPersonWithReadUnCommitted")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
    public boolean getPersonPersonWithReadUnCommitted() {
        /*
          <h1>Isolation.READ_UNCOMMITTED</h1>
           This is the lowest isolation level, allowing transactions to read uncommitted changes made by
           other transactions. It may lead to dirty reads, non-repeatable reads, and phantom reads.

           Available supported database,

           1) ORACLE : This Isolation.READ_UNCOMMITTED doesn't support by Oracle.
           2) MySql : This Isolation.READ_UNCOMMITTED support in mysql.

           So, For test this Isolation level you should use Mysql database or others which support this isolation.

          <b> I am using MySql database for test this isolation level.</b>
         */
        getPersonPersonWithReadUnCommitted(personRepository);
        return true;
    }


    @GetMapping("/getPersonPersonWithRepeatableRead")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public boolean getPersonPersonWithRepeatableRead() {
        /*
        REPEATABLE_READ (Isolation.REPEATABLE_READ):
                        This isolation level prevents non-repeatable reads,
                         meaning that once a transaction reads a value,
                         it will always see the same value,
                         even if other transactions modify it. However, it may still allow phantom reads.


        it always read committed data when current transaction wanna access the data and within this process if another transaction
        change the data then if this transaction wanna again get data from same row it will show the previous data not the updated
        committed data which is updated and committed by another transaction
         */
        getPersonPersonWithRepeatableRead(personRepository);
        return true;
    }

    @GetMapping("/updatePersonPersonWithSerializable")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public boolean updatePersonWithSerializable() {
        //@TODO not tested till now..need to test by concrete call by jmitter with another service
        /*
       Serializable (Isolation Level 3): This is the highest isolation level. Service B will effectively
             have to wait until Service A's transaction is complete before it can read or update the row.
             It guarantees that the data remains consistent but can lead to potential performance issues due to locking.

             In your scenario, if you want to avoid Service B reading the uncommitted change made by Service A, you should use
             at least the "Read Committed" isolation level. Depending on your specific requirements for data consistency
             and performance, you can choose a higher isolation level like "Repeatable Read" or "Serializable."

             Keep in mind that higher isolation levels come with increased locking and potential performance implications,
             so it's essential to choose the appropriate level based on your application's specific needs.
  */
        updatePersonWithSerializable(personRepository);
        return true;
    }

}

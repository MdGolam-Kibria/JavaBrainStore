package com.CrackCode.transactionalBehaviour;

import com.CrackCode.entity.Person;
import com.CrackCode.repository.PersonRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class Helper {
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public void getPersonWithDefaultIsolation(PersonRepository personRepository) {
        Person person = personRepository.findById(41).get();
        System.out.println(person.getBalance());
        System.out.println("done");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
    public void getPersonPersonWithReadUnCommitted(PersonRepository personRepository) {
        Person person = personRepository.findById(41).get();
        System.out.println(person.getBalance());
        System.out.println("done");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public void getPersonPersonWithRepeatableRead(PersonRepository personRepository) {
        Person person = personRepository.findById(41).get();
        System.out.println(person.getBalance());
        System.out.println("done");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void updatePersonWithSerializable(PersonRepository personRepository) {
        Person person = personRepository.findById(41).get();
        System.out.println(person.getBalance());
        System.out.println("done");
        System.out.println("Now going to modification");
        person.setBalance(person.getBalance() + 10);
        person = personRepository.save(person);
        System.out.println("Done Modification");
    }
}

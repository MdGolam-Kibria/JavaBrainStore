package com.CrackCode.database;

import com.CrackCode.database.INNER_JOIN.model.Child;
import com.CrackCode.database.INNER_JOIN.model.Parent;
import com.CrackCode.database.INNER_JOIN.repository.ChildRepository;
import com.CrackCode.database.INNER_JOIN.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class INNER_JOIN_WORKER {
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;

    @Autowired
    public INNER_JOIN_WORKER(ParentRepository parentRepository, ChildRepository childRepository) {
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
    }

    //@PostConstruct
    public void add() {
        if (parentRepository.countAllByIsActiveTrue() < 2) {
            Parent parent = new Parent();
            parent.setName("sayeem");
            parent.setEmail("sayeem@gmail.com");
            parent.setPassword("413152413125");
            parent.setAge("12");
            parent = parentRepository.save(parent);
        }
        if (parentRepository.countAllByIsActiveTrue() < 2) {
            Child child = new Child();
            child.setName("helal");
            child.setEmail("helal@gmail.com");
            child.setPassword("413152413125");
            child.setAge("12");
            child.setNationality("Bangladesh");
            child = childRepository.save(child);
        }
    }
}

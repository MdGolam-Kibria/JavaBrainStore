package com.CrackCode.interviewQuestion.database.LEFT_JOIN.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Boolean isActive;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> orders;

    @PrePersist
    public void setPreInsertData() {
        this.isActive = true;
    }
}

package com.CrackCode.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "PERSON")
public class Person  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "AGE")

    private Integer age;
    @Column(name = "EMAIL")

    private String email;
    @Column(name = "NAME")

    private String name;
    @Column(name = "BALANCE")

    private Double balance;
}

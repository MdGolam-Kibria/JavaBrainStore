package com.CrackCode.interviewQuestion.database.LEFT_JOIN.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;
    private Double amount;
    private Boolean isActive;

    @PrePersist
    public void setPreInsertData() {
        this.isActive = true;
        this.orderDate = new Date();
    }

}

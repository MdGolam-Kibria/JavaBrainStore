package com.CrackCode.interviewQuestion.database.LEFT_JOIN.repository;

import com.CrackCode.interviewQuestion.database.LEFT_JOIN.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}

package com.CrackCode.database.LEFT_JOIN.repository;

import com.CrackCode.database.LEFT_JOIN.model.Customer;
import com.CrackCode.database.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = SQL.getAllCustomerNameAndAllOrderAmount,nativeQuery = true)
    List<Map<String, Object>> getAllCustomerNameAndAllOrderAmount();//LEFT JOIN

}

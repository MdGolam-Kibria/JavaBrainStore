package com.CrackCode.database.test;

import com.CrackCode.database.INNER_JOIN.repository.ParentRepository;
import com.CrackCode.database.LEFT_JOIN.repository.CustomerRepository;
import com.CrackCode.database.SQL;
import com.CrackCode.database.entityManagerHelper.EntityManagerQueryHelper;
import com.CrackCode.database.entityManagerHelper.InnerJoinResponseAuto;
import com.CrackCode.database.entityManagerHelper.LeftJoinResponseAuto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final ParentRepository parentRepository;
    private final CustomerRepository customerRepository;
    private final EntityManagerQueryHelper helper;

    public TestController(ParentRepository parentRepository, CustomerRepository customerRepository, EntityManagerQueryHelper helper) {
        this.parentRepository = parentRepository;
        this.customerRepository = customerRepository;
        this.helper = helper;
    }

    @GetMapping("/innerJoinTestEntityManagerTest")//automated response
    public Object getEntityManageSqlQueryResult(){
        return helper.getQueryResult(SQL.getChildNationalityByParentIdentity,new InnerJoinResponseAuto());
    }
    @GetMapping("/leftJoinTestEntityManagerTest")//automated response
    public Object getEntityManageSqlQueryResultLeft(){
        return helper.getQueryResult(SQL.getAllCustomerNameAndAllOrderAmount,new LeftJoinResponseAuto());
    }
    @GetMapping("/innerJoinTest")
    public Object getChildNationalityByMatchingParentChildId(){//INNER JOIN
       return parentRepository.getChildNationalityByMatchingParentChildId();
    }
    @GetMapping("/leftJoinTest")
    public Object getAllCustomerNameAndAllOrderAmountTest(){//LEFT JOIN
        return customerRepository.getAllCustomerNameAndAllOrderAmount();
    }
}

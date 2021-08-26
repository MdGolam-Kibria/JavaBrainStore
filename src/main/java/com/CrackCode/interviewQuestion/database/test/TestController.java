package com.CrackCode.interviewQuestion.database.test;

import com.CrackCode.interviewQuestion.database.INNER_JOIN.repository.ParentRepository;
import com.CrackCode.interviewQuestion.database.LEFT_JOIN.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final ParentRepository parentRepository;
    private final CustomerRepository customerRepository;

    public TestController(ParentRepository parentRepository, CustomerRepository customerRepository) {
        this.parentRepository = parentRepository;
        this.customerRepository = customerRepository;
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

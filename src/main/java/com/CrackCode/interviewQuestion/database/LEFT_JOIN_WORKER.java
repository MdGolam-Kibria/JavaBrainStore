package com.CrackCode.interviewQuestion.database;

import com.CrackCode.interviewQuestion.database.LEFT_JOIN.model.Customer;
import com.CrackCode.interviewQuestion.database.LEFT_JOIN.model.Order;
import com.CrackCode.interviewQuestion.database.LEFT_JOIN.repository.CustomerRepository;
import com.CrackCode.interviewQuestion.database.LEFT_JOIN.repository.OrderRepository;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Configuration
public class LEFT_JOIN_WORKER {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public LEFT_JOIN_WORKER(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void setDate() {
        Customer customer = new Customer();
        customer.setName("manik");
        customer.setAddress("Dhaka 1205");

        Order order = new Order();
        order.setAmount(1500.0);

        customer.setOrders(Collections.singletonList(order));
        customer = customerRepository.save(customer);
    }
}

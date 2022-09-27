package com.amigoscode.customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    List<Customer> findAll();

    Customer findById(Integer id);

    Customer update(Customer newCustomer, Integer id);

    void delete(Integer id);
}

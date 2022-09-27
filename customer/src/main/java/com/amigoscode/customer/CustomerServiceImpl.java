package com.amigoscode.customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CustomerServiceImpl(CustomerRepository repository) implements CustomerService {
    @Override
    public Customer create(Customer customer) {

        // TODO check if email valid
        // TODO check if email not taken

        return repository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Customer update(Customer newCustomer, Integer id) {
        Customer customer = findById(id);
        customer.setFirstName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());
        customer.setEmail(newCustomer.getEmail());

        return repository.save(customer);
    }

    @Override
    public void delete(Integer id) {

    }
}

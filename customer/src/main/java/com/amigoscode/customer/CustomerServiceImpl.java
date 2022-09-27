package com.amigoscode.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final RestTemplate restTemplate;

    @Override
    public Customer create(Customer customerRequest) {

        // TODO check if email valid
        // TODO check if email not taken

        Customer customer = repository.saveAndFlush(customerRequest);
        FraudResponse fraudResponse = restTemplate.getForObject("http://localhost:8081/api/v1/fraud/{customerId}",
                FraudResponse.class,
                customer.getId());

        if (fraudResponse != null && fraudResponse.isFraudster()) {
            throw new IllegalCallerException("Customer is fraudster");
        }

        return customer;
        // TODO send notification
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
        repository.deleteById(id);
    }
}

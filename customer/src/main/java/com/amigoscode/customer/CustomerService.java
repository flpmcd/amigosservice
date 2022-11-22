package com.amigoscode.customer;

import com.amigoscode.amqp.RabbitMQMessageProducer;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.fraud.dto.FraudResponse;
import com.amigoscode.clients.notification.NotificationClient;
import com.amigoscode.clients.notification.dto.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
//    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer producer;

    public Customer create(Customer customerRequest) {

        // TODO check if email valid
        // TODO check if email not taken

        Customer customer = repository.saveAndFlush(customerRequest);
//        FraudResponse fraudResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud/{customerId}",
//                FraudResponse.class,
//                customer.getId());

        FraudResponse fraudResponse = fraudClient.isFraudster(customer.getId());

        if (fraudResponse != null && fraudResponse.isFraudster()) {
            throw new IllegalCallerException("Customer is fraudster");
        }

        NotificationRequest request = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "Hi %s, welcome to amigoscode"
        );
//        notificationClient.sendNotification(new NotificationRequest("Customer created!"));
        producer.publish(request, "internal.exchange", "internal.notification.routing-key");
        return customer;
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public Customer update(Customer newCustomer, Integer id) {
        Customer customer = findById(id);
        customer.setFirstName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());
        customer.setEmail(newCustomer.getEmail());

        return repository.save(customer);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

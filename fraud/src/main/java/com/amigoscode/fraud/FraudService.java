package com.amigoscode.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FraudService {

    private final FraudRepository repository;

    public boolean isFraudulentCustomer(Integer customerId) {
        return false;
    }
}

package com.amigoscode.fraud;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud/")
@AllArgsConstructor
public class FraudController {

    private final FraudService service;

    @GetMapping("{customerId}")
    public FraudResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        return new FraudResponse(service.isFraudulentCustomer(customerId));
    }
}

package com.amigoscode.fraud;

import com.amigoscode.clients.fraud.dto.FraudResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud/")
@AllArgsConstructor
public class FraudController {

    private final FraudService service;

    @GetMapping("{customerId}")
    public FraudResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        log.info("Fraud check request for customer {}", customerId);

        return new FraudResponse(service.isFraudulentCustomer(customerId));
    }
}

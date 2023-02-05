package com.amigoscode.clients.fraud;

import com.amigoscode.clients.fraud.dto.FraudResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "fraud",
        url = "${clients.fraud.url}"
)
public interface FraudClient {

    @GetMapping("/api/v1/fraud/{customerId}")
    FraudResponse isFraudster(@PathVariable("customerId") Integer customerId);
}

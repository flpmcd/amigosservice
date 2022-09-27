package com.amigoscode.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService service) {

    @PostMapping
    public Customer create(@RequestBody CustomerRequest request) {
        log.info("Registration customer {} - Start ", request);
        return service.create(request.toModel());
    }

    @GetMapping("/{id}")
    public Customer find(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody CustomerRequest request, @PathVariable Integer id) {
        return service.update(request.toModel(), id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}

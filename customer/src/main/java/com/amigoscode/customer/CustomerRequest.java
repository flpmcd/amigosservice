package com.amigoscode.customer;

public record CustomerRequest(
        String firstName,
        String lastName,
        String email
) {
    public Customer toModel() {
        return Customer.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .build();
    }
}

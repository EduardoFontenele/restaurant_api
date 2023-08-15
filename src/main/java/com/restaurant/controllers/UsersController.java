package com.restaurant.controllers;

import com.restaurant.dtos.customer.CustomerRegisterInputDTO;
import com.restaurant.dtos.customer.CustomerRegisterOutputDTO;
import com.restaurant.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class UsersController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerRegisterOutputDTO> registerCustomer(@RequestBody @Validated CustomerRegisterInputDTO customer) {
        return new ResponseEntity<>(customerService.registerNewCustomer(customer), HttpStatus.CREATED);
    }


}

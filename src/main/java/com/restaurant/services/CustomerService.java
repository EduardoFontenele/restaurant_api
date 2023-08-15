package com.restaurant.services;

import com.restaurant.dtos.customer.CustomerRegisterInputDTO;
import com.restaurant.dtos.customer.CustomerRegisterOutputDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {
    CustomerRegisterOutputDTO registerNewCustomer(CustomerRegisterInputDTO customer);
}

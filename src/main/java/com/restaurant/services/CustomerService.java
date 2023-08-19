package com.restaurant.services;

import com.restaurant.dtos.customer.CustomerRegisterInputDTO;
import com.restaurant.dtos.customer.CustomerRegisterOutputDTO;

public interface CustomerService {
    CustomerRegisterOutputDTO registerNewCustomer(CustomerRegisterInputDTO customer);
}

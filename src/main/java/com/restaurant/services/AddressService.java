package com.restaurant.services;

import com.restaurant.dtos.address.AddressGetDTO;
import com.restaurant.dtos.address.AddressRegisterInputDTO;

public interface AddressService {
    AddressGetDTO saveNewCustomerAddress(AddressRegisterInputDTO dto);
}

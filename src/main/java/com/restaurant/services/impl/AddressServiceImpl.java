package com.restaurant.services.impl;

import com.restaurant.dtos.address.AddressGetDTO;
import com.restaurant.dtos.address.AddressRegisterInputDTO;
import com.restaurant.entities.Address;
import com.restaurant.entities.Customer;
import com.restaurant.repositories.AddressRepository;
import com.restaurant.repositories.CustomerRepository;
import com.restaurant.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Override
    public AddressGetDTO saveNewCustomerAddress(AddressRegisterInputDTO dto) {
        Customer foundCustomer = customerRepository.findById(dto.getCustomerId()).get();
        Address savedAddress = addressRepository.save(new Address(
                foundCustomer,
                dto.getStreet(),
                dto.getNumber(),
                dto.getComplement())
        );
        return new AddressGetDTO(savedAddress.getStreet(), savedAddress.getNumber(), savedAddress.getComplement());
    }
}

package com.restaurant.mappers;

import com.restaurant.dtos.address.AddressRegisterInputDTO;
import com.restaurant.entities.Address;
import com.restaurant.repositories.AddressRepository;
import com.restaurant.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public Address registerInputDtoToEntity(AddressRegisterInputDTO dto) {
        return Address.builder().build();
    }
}

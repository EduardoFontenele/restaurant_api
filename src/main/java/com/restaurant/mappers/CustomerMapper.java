package com.restaurant.mappers;

import com.restaurant.dtos.customer.CustomerRegisterInputDTO;
import com.restaurant.dtos.customer.CustomerRegisterOutputDTO;
import com.restaurant.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomerMapper {


    private final ModelMapper modelMapper;

    public Customer customerRegisterDtoToEntity(CustomerRegisterInputDTO dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Customer.class);
    }

    public CustomerRegisterOutputDTO customerEntityToRegisterDto (Customer entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, CustomerRegisterOutputDTO.class);
    }
}

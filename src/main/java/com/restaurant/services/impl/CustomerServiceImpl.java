package com.restaurant.services.impl;

import com.restaurant.dtos.customer.CustomerRegisterInputDTO;
import com.restaurant.dtos.customer.CustomerRegisterOutputDTO;
import com.restaurant.entities.Customer;
import com.restaurant.mappers.CustomerMapper;
import com.restaurant.repositories.CustomerRepository;
import com.restaurant.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public UserDetails loadUserByUsername(String passedEmail) throws UsernameNotFoundException {
        String userEmail, password;
        List<GrantedAuthority> authorities;
        Customer foundCustomer = customerRepository.findByEmail(passedEmail);
        if(Objects.isNull(foundCustomer)) {
            throw new UsernameNotFoundException(String
                    .format("User details not found for following email: %s", passedEmail));
        } else {
            userEmail = foundCustomer.getEmail();
            password = foundCustomer.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(foundCustomer.getRole()));
        }

        return new User(userEmail, password, authorities);
    }

    @Override
    public CustomerRegisterOutputDTO registerNewCustomer(CustomerRegisterInputDTO customerRegisterInputDTO) {
        Customer savedCustomer = customerMapper.customerRegisterDtoToEntity(customerRegisterInputDTO);
        return customerMapper.customerEntityToRegisterDto(customerRepository.save(savedCustomer));
    }
}

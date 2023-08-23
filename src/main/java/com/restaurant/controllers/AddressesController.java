package com.restaurant.controllers;

import com.restaurant.dtos.address.AddressGetDTO;
import com.restaurant.dtos.address.AddressRegisterInputDTO;
import com.restaurant.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressesController {

    private final AddressService addressService;

    @PostMapping("/register")
    public ResponseEntity<AddressGetDTO> saveCustomerAddress(@RequestBody @Validated AddressRegisterInputDTO dto) {
        return new ResponseEntity<>(addressService.saveNewCustomerAddress(dto), HttpStatus.CREATED);
    }
}

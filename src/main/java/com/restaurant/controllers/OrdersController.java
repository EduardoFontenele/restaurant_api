package com.restaurant.controllers;

import com.restaurant.dtos.order.CostumerOrderGetDTO;
import com.restaurant.dtos.order.OrderPostInputDTO;
import com.restaurant.dtos.order.OrderPostOutputDTO;
import com.restaurant.services.OrderService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderPostOutputDTO> createNewOrder(@Validated @RequestBody OrderPostInputDTO dto) {
        return new ResponseEntity<>(orderService.createNewOrder(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CostumerOrderGetDTO>> listPreviousCostumerOrders() {
        return new ResponseEntity<>(orderService.listPreviousCostumerOrders(), HttpStatus.OK);
    }
}

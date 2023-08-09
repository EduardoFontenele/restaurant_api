package com.restaurant.services;

import com.restaurant.dtos.order.OrderPostInputDTO;
import com.restaurant.dtos.order.OrderPostOutputDTO;

public interface OrderService {
    OrderPostOutputDTO createNewOrder(OrderPostInputDTO dto);
}

package com.restaurant.services;

import com.restaurant.dtos.order.CustomerOrderGetDTO;
import com.restaurant.dtos.order.OrderPostInputDTO;
import com.restaurant.dtos.order.OrderPostOutputDTO;

import java.util.List;

public interface OrderService {
    OrderPostOutputDTO createNewOrder(OrderPostInputDTO dto);

    List<CustomerOrderGetDTO> listPreviousCostumerOrders();
}

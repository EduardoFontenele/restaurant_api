package com.restaurant.services;

import com.restaurant.dtos.order.CostumerOrderGetDTO;
import com.restaurant.dtos.order.OrderPostInputDTO;
import com.restaurant.dtos.order.OrderPostOutputDTO;

import java.util.List;

public interface OrderService {
    OrderPostOutputDTO createNewOrder(OrderPostInputDTO dto);

    List<CostumerOrderGetDTO> listPreviousCostumerOrders();
}

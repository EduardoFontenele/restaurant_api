package com.restaurant.services.impl;

import com.restaurant.dtos.address.AddressGetDTO;
import com.restaurant.dtos.meal.MealGetDTO;
import com.restaurant.dtos.order.CustomerOrderGetDTO;
import com.restaurant.dtos.order.OrderMealInputDTO;
import com.restaurant.dtos.order.OrderMealOutputDTO;
import com.restaurant.dtos.order.OrderPostInputDTO;
import com.restaurant.dtos.order.OrderPostOutputDTO;
import com.restaurant.entities.Address;
import com.restaurant.entities.Order;
import com.restaurant.exceptions.BusinessException;
import com.restaurant.exceptions.ErrorsTable;
import com.restaurant.mappers.OrderMapper;
import com.restaurant.repositories.AddressRepository;
import com.restaurant.repositories.OrderRepository;
import com.restaurant.services.MealService;
import com.restaurant.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MealService mealService;
    private final AddressRepository addressRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderPostOutputDTO createNewOrder(OrderPostInputDTO dto) {
        BigDecimal totalOrderPrice = new BigDecimal(0);
        List<OrderMealOutputDTO> orderMealDTOS = new Vector<>();

        for(OrderMealInputDTO inputMeal : dto.getOrderedMeals()) {
            if(inputMeal.getQuantity() < 1 || inputMeal.getId() < 1)
                throw new BusinessException(ErrorsTable.ERROR_CREATING_ORDER);

            MealGetDTO mealGetDTO = mealService.findMealById(inputMeal.getId());
            OrderMealOutputDTO outputMealDto = generateMealOrderOutputDTO(inputMeal, mealGetDTO);
            orderMealDTOS.add(outputMealDto);
        }

        for(OrderMealOutputDTO mealOutputDto : orderMealDTOS) {
            totalOrderPrice = totalOrderPrice.add(mealOutputDto.getTotalPrice(), new MathContext(8));
        }

        Order savedEntity = orderRepository.save(new Order(dto.getCustomerName(), totalOrderPrice));

        DateTimeFormatter formatterOrderTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String orderTime = savedEntity.getOrderTime().format(formatterOrderTime);

        DateTimeFormatter formatterOrderDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String orderDate = savedEntity.getOrderTime().format(formatterOrderDate);

        Address customerAddress = addressRepository.findById(dto.getCustomerAddressId()).get();

        return OrderPostOutputDTO.builder()
                .orderedMeals(orderMealDTOS)
                .customerName(savedEntity.getCustomerName())
                .orderTime(orderTime)
                .orderDate(orderDate)
                .totalPrice(savedEntity.getTotalPrice())
                .address(new AddressGetDTO(customerAddress.getStreet(), customerAddress.getNumber(), customerAddress.getComplement()))
                .build();
    }

    private static OrderMealOutputDTO generateMealOrderOutputDTO(OrderMealInputDTO inputMeal, MealGetDTO mealGetDTO) {
        OrderMealOutputDTO orderMealOutputDTO = new OrderMealOutputDTO();
        BigDecimal totalMealPrice = new BigDecimal(mealGetDTO.getPrice().toString());

        totalMealPrice = totalMealPrice.multiply(BigDecimal
                .valueOf(inputMeal.getQuantity()), new MathContext(8));

        orderMealOutputDTO.setName(mealGetDTO.getName());
        orderMealOutputDTO.setPrice(mealGetDTO.getPrice());
        orderMealOutputDTO.setQuantity(inputMeal.getQuantity());
        orderMealOutputDTO.setTotalPrice(totalMealPrice);
        return orderMealOutputDTO;
    }

    @Override
    public List<CustomerOrderGetDTO> listPreviousCostumerOrders() {
        return orderRepository.findAll().stream().map(orderMapper::orderEntityToCostumerGetDto).toList();
    }
}

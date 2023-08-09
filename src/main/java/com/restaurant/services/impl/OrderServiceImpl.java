package com.restaurant.services.impl;

import com.restaurant.dtos.meal.MealGetDTO;
import com.restaurant.dtos.order.MealOrderInputDTO;
import com.restaurant.dtos.order.MealOrderOutputDTO;
import com.restaurant.dtos.order.OrderPostInputDTO;
import com.restaurant.dtos.order.OrderPostOutputDTO;
import com.restaurant.entities.Order;
import com.restaurant.mappers.OrderMapper;
import com.restaurant.repositories.OrderRepository;
import com.restaurant.services.MealService;
import com.restaurant.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Vector;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MealService mealService;
    private final OrderMapper orderMapper;

    @Override
    public OrderPostOutputDTO createNewOrder(OrderPostInputDTO dto) {
        BigDecimal totalOrderPrice = new BigDecimal(0);
        MealOrderOutputDTO outputOrderDto = new MealOrderOutputDTO();
        List<MealOrderOutputDTO> orderMealDTOS = new Vector<>();

        for(MealOrderInputDTO inputMeal : dto.getOrderedMeals()) {
            MealGetDTO mealGetDTO = mealService.findMealById(inputMeal.getId());
            MealOrderOutputDTO outputMealDto = new MealOrderOutputDTO();
            BigDecimal totalMealPrice = new BigDecimal(mealGetDTO.getPrice().toString());

            MathContext mc = new MathContext(4);
            totalMealPrice = totalMealPrice.multiply(BigDecimal.valueOf(inputMeal.getQuantity()), mc);

            outputMealDto.setName(mealGetDTO.getName());
            outputMealDto.setPrice(mealGetDTO.getPrice());
            outputMealDto.setQuantity(inputMeal.getQuantity());
            outputMealDto.setTotalPrice(totalMealPrice);
            orderMealDTOS.add(outputMealDto);
        }

        for(MealOrderOutputDTO mealOutputDto : orderMealDTOS) {
            totalOrderPrice = totalOrderPrice.add(mealOutputDto.getTotalPrice(), new MathContext(4));
        }

        Order entity = new Order(dto.getCostumerName(), totalOrderPrice);
        Order savedEntity = orderRepository.save(entity);

        return OrderPostOutputDTO.builder()
                .orderedMeals(orderMealDTOS)
                .costumerName(savedEntity.getCostumerName())
                .orderTime(savedEntity.getOrderTime())
                .totalPrice(savedEntity.getTotalPrice())
                .build();
    }
}

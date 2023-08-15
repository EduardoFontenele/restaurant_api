package com.restaurant.mappers;

import com.restaurant.dtos.meal.MealGetDTO;
import com.restaurant.dtos.order.CustomerOrderGetDTO;
import com.restaurant.dtos.order.OrderMealInputDTO;
import com.restaurant.entities.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMealInputDTO mealGetDtoToOrderMeal(MealGetDTO mealGetDTO) {
        return Objects.isNull(mealGetDTO) ? null : modelMapper.map(mealGetDTO, OrderMealInputDTO.class);
    }

    public CustomerOrderGetDTO orderEntityToCostumerGetDto(Order entity) {
        CustomerOrderGetDTO outputDto = new CustomerOrderGetDTO();

        DateTimeFormatter formatterOrderTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter formatterOrderDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String formattedOrderTime = entity.getOrderTime().format(formatterOrderTime);
        String formattedOrderDate = entity.getOrderTime().format(formatterOrderDate);

        outputDto.setId(entity.getId());
        outputDto.setOrderTime(formattedOrderTime);
        outputDto.setOrderDate(formattedOrderDate);
        outputDto.setTotalPrice(entity.getTotalPrice());
        outputDto.setCustomerName(entity.getCustomerName());

        return outputDto;

    }
}

package com.restaurant.mappers;

import com.restaurant.dtos.meal.MealGetDTO;
import com.restaurant.dtos.order.MealOrderInputDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ModelMapper modelMapper;

    public MealOrderInputDTO mealGetDtoToOrderMeal(MealGetDTO mealGetDTO) {
        return Objects.isNull(mealGetDTO) ? null : modelMapper.map(mealGetDTO, MealOrderInputDTO.class);
    }
}

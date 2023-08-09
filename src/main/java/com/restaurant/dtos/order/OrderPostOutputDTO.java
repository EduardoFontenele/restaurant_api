package com.restaurant.dtos.order;

import com.restaurant.dtos.meal.MealGetDTO;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPostOutputDTO {

    private String costumerName;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal totalPrice;

    private LocalDateTime orderTime;

    private List<MealOrderOutputDTO> orderedMeals;
}

package com.restaurant.dtos.order;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPostOutputDTO {

    private String customerName;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal totalPrice;

    private String orderDate;
    private String orderTime;
    private List<OrderMealOutputDTO> orderedMeals;
}

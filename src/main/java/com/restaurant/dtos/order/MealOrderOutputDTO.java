package com.restaurant.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealOrderOutputDTO {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;

}

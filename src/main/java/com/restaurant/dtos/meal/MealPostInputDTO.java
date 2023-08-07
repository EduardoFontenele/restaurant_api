package com.restaurant.dtos.meal;

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
public class MealPostInputDTO {
    private String name;
    private BigDecimal price;
    private Long menuSectionId;
}

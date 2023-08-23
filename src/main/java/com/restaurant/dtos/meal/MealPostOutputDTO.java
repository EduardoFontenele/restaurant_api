package com.restaurant.dtos.meal;

import com.restaurant.dtos.menuSection.MenuSectionPostOutputDTO;
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
public class MealPostOutputDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private MenuSectionPostOutputDTO menuSection;
}

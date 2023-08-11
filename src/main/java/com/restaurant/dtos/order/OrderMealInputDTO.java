package com.restaurant.dtos.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderMealInputDTO {

    @NotNull(message = "'id' field can't be null")
    @Min(value = 1, message = "'id' field can't be negative or zero")
    private Long id;

    @Min(value = 1, message = "'quantity' field can't be negative or zero")
    private Integer quantity;
}

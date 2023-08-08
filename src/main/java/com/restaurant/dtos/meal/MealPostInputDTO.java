package com.restaurant.dtos.meal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 3, max = 255, message = "Size must be 3-255 characters")
    private String name;

    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0", message = "Price can't be negative")
    @DecimalMax(value = "100", message = "Price can't be bigger than 100")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal price;

    @NotNull(message = "Menu Section ID can't be null")
    @Min(value = 1, message = "Menu Section ID can't be negative or zero")
    private Long menuSectionId;
}

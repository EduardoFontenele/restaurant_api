package com.restaurant.dtos.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPostInputDTO {

    @NotEmpty(message = "'costumerName' can't be empty")
    @Size(min = 3, max = 255, message = "Size must be 3-255 characters")
    private String costumerName;

    @NotNull
    @JsonInclude
    private List<OrderMealInputDTO> orderedMeals;
}

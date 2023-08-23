package com.restaurant.dtos.address;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class AddressRegisterInputDTO {

    @NotNull(message = "customerId can't be null")
    @Min(value = 1, message = "No negative values")
    private Long customerId;

    @NotEmpty(message = "street can't be empty")
    @Size(min = 3, max = 400, message = "Size must be 3-400 characters")
    private String street;

    @NotNull(message = "number cannot be null")
    private Integer number;

    @Size(min = 3, max = 400, message = "Size must be 3-400 characters")
    private String complement;

}

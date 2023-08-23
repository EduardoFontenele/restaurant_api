package com.restaurant.dtos.menuSection;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class MenuSectionPostInputDTO {
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 3, max = 255, message = "Size must be 3-255 characters")
    private String name;
}

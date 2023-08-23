package com.restaurant.dtos.customer;

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
public class CustomerRegisterInputDTO {
    @Email
    @NotEmpty(message = "Email can't be empty")
    @Size(min = 3, max = 255, message = "Size must be 3-255 characters")
    private String email;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 3, max = 255, message = "Size must be 3-255 characters")
    private String password;

    @NotEmpty(message = "Role can't be empty")
    @Size(min = 3, max = 45, message = "Size must be 3-45 characters")
    private String role;

}

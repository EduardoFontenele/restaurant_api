package com.restaurant.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorsTable {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Error registering item [item]"),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "Item [item] could not be found"),
    ERROR_CREATING_ORDER(HttpStatus.BAD_REQUEST, "Error creating order. 'id' or 'quantity' fields are invalid" +
            " in one of meal's order"),
    RESOURCE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Item [item] already exists"),
    PATH_VARIABLE_ERROR(HttpStatus.BAD_REQUEST, "Error on path variable, value is invalid or null");

    private final HttpStatus httpStatus;
    private final String error;
}

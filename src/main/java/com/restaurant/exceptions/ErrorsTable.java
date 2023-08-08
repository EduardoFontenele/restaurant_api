package com.restaurant.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorsTable {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Error registering item [item]"),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "Item [item] could not be found"),
    PATH_VARIABLE_ERROR(HttpStatus.BAD_REQUEST, "Error on path variable, value is invalid or null");

    private final HttpStatus httpStatus;
    private final String error;
}

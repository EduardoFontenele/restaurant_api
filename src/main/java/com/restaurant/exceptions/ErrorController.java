package com.restaurant.exceptions;

import com.restaurant.dtos.validation.ErrorDTO;
import com.restaurant.dtos.validation.ValidationDTO;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> handleException(BusinessException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMessage(e.getError());
        errorDTO.setValidationErrorList(e.getValidationDTOS());

        return ResponseEntity.status(e.getHttpStatus()).body(errorDTO);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorDTO> handle(BindException e) {
        List<ValidationDTO> validationsList = new ArrayList<>();

        for(FieldError bindingResult : e.getBindingResult().getFieldErrors()) {
            validationsList.add(new ValidationDTO(bindingResult.getField(), bindingResult.getDefaultMessage()));
        }

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMessage(ErrorsTable.VALIDATION_ERROR.getError());
        errorDTO.setValidationErrorList(validationsList);

        return ResponseEntity.status(ErrorsTable.VALIDATION_ERROR.getHttpStatus()).body(errorDTO);
    }

}

package com.restaurant.exceptions;

import com.restaurant.dtos.validation.ValidationDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class BusinessException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;
    private final String error;
    private final List<ValidationDTO> validationDTOS;

    public BusinessException(ErrorsTable table, String item, List<ValidationDTO> validationDTOS) {
       super();
       this.httpStatus = table.getHttpStatus();
       this.error = table.getError().replace("[item]", item);
       this.validationDTOS = validationDTOS;
    }

    public BusinessException(ErrorsTable table, String item) {
        super();
        this.httpStatus = table.getHttpStatus();
        this.error = table.getError().replace("[item]", item);
        this.validationDTOS = new ArrayList<>();
    }

    public BusinessException(ErrorsTable table) {
        super();
        this.httpStatus = table.getHttpStatus();
        this.error = table.getError().replace("[item]", "");
        this.validationDTOS = new ArrayList<>();
    }
}

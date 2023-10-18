package com.shopall.app.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

    private String message;
    private Integer code;
    private String status;


    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(HttpStatus httpStatus, String message){
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

}

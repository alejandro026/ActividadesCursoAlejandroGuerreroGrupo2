package com.shopall.app.exceptions;


import com.shopall.app.models.entity.Response;
import org.hibernate.JDBCException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Se hace el manejo se exepciones
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response<?>> handlePostBusinessException(BusinessException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getHttpEstatus(), ex.getMessage());
        return new ResponseEntity(errorResponse, ex.getHttpEstatus());
    }

    //Se hace el menejo de exepciones del error response.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus status =  HttpStatus.BAD_REQUEST;
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError.getDefaultMessage();
        return new ResponseEntity<>(new ErrorResponse(status,errorMessage),status);
    }

}
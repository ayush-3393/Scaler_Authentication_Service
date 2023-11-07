package com.auth.scalerauthservice.controllers;

import com.auth.scalerauthservice.dtos.ErrorMessageDto;
import com.auth.scalerauthservice.exceptions.NotFoundException;
import com.auth.scalerauthservice.exceptions.TokenGenerationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler({NotFoundException.class, TokenGenerationException.class})
    public ResponseEntity<ErrorMessageDto> handleNotFoundException(Exception exception){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.NOT_FOUND);
    }
}

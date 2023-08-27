package com.project.travello_backend.ExceptionHandlers;


import com.project.travello_backend.RequestEntity.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;

@ControllerAdvice
public class InvalidTokenHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorEntity> handleInvalidTokenException(SignatureException exc){
        ErrorEntity errorRes = new ErrorEntity();
        errorRes.setError("invalid token exception");
        errorRes.setMessage("Invalid token, please provide valid token!!");
        return new ResponseEntity<>(errorRes, HttpStatus.FORBIDDEN);

    }
}

package com.project.travello_backend.ExceptionHandlers;

import com.project.travello_backend.RequestEntity.ErrorEntity;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExpiredTokenHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorEntity> handleException(ExpiredJwtException exc){
        ErrorEntity errorRes = new ErrorEntity();
        errorRes.setError("Expired token exception");
        errorRes.setMessage("Session Expired please login again!!");
        return new ResponseEntity<>(errorRes, HttpStatus.FORBIDDEN);

    }
}

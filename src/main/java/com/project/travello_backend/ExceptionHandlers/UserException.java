package com.project.travello_backend.ExceptionHandlers;

import com.project.travello_backend.RequestEntity.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserException {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorEntity> handleUserNotException(UserNotFoundException exc){
        ErrorEntity errorRes = new ErrorEntity();
        errorRes.setError("Customer not found");
        errorRes.setMessage("No customer found with this email please register");
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorEntity> handleUserExistException(UserAlreadyExistException exc){
        ErrorEntity errorRes = new ErrorEntity();
        errorRes.setError("Customer Already exist");
        errorRes.setMessage("please register with different email");
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorEntity> handleBadCredentialsException(BadCredentialsException exc){
        ErrorEntity errorRes = new ErrorEntity();
        errorRes.setError("wrong password");
        errorRes.setMessage("please enter correct password");
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomExistsException.class)
    public ResponseEntity<ErrorEntity> handleRoomExistsException(RoomExistsException exc){
        ErrorEntity errorRes = new ErrorEntity();
        errorRes.setError("room already exists");
        errorRes.setMessage("please enter new room number");
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingsFullException.class)
    public ResponseEntity<ErrorEntity> handleBookingFullException(BookingsFullException exc){
        ErrorEntity errorRes = new ErrorEntity();
        errorRes.setError("No rooms avaialable ");
        errorRes.setMessage("Sorry no Rooms Available");
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
    }



}

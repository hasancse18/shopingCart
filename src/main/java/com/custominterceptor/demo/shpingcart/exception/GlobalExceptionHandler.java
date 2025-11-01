package com.custominterceptor.demo.shpingcart.exception;

import com.custominterceptor.demo.shpingcart.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse>productNotFoundException(ProductNotFoundException productNotFoundException)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(productNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<?> jsonProcessingException(JsonProcessingException jsonProcessingException)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Check your Json Data");
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> productAlreadyExistException(AlreadyExistException alreadyExistException)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Product Already Exist");
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(resourceNotFoundException.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

}

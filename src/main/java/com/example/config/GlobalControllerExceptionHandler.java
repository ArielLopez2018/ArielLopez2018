package com.example.config;

import com.example.model.response.ErrorMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMsgDto> handleRenaperClientError(Exception ex) {
        ErrorMsgDto error = new ErrorMsgDto("ERROR", ex.getMessage());
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMsgDto> responseIllegalArgumentException(IllegalArgumentException ex) {
        ErrorMsgDto error = new ErrorMsgDto("ERROR", ex.getMessage());
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}

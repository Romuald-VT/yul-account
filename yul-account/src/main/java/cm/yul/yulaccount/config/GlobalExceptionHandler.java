package cm.yul.yulaccount.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cm.yul.yulaccount.entities.Error;
import cm.yul.yulaccount.exceptions.EntityAlReadyExistException;
import cm.yul.yulaccount.exceptions.EntityNotFoundException;

@ControllerAdvice
@Configuration
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>handleException(Exception e)
    {
        Error error = new Error();
        error.setErrorCode(500);
        error.setErrorMessage(e.getMessage());

        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException e)
    {
        Error error = new Error();
        error.setErrorCode(404);
        error.setErrorMessage(e.getMessage());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(EntityAlReadyExistException.class)
    public ResponseEntity<?> handleEntityAlreadyExist(EntityAlReadyExistException e)
    {
        Error error = new Error();
        error.setErrorCode(201);
        error.setErrorMessage(e.getMessage());

        return new ResponseEntity<>(error,HttpStatus.CREATED);
    }
}

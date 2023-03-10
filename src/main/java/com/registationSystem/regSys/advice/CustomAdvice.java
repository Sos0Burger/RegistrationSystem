package com.registationSystem.regSys.advice;

import com.registationSystem.regSys.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomAdvice {
    @ExceptionHandler(CreationException.class)
    public ResponseEntity<?> handleException(CreationException e) {
        return new ResponseEntity<>("Ошибка создания: "+ e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(FindException.class)
    public ResponseEntity<?> handleException(FindException e) {
        return new ResponseEntity<>("Ошибка поиска: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UpdateException.class)
    public ResponseEntity<?> handleException(UpdateException e) {
        return new ResponseEntity<>("Ошибка обновления данных: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DeleteException.class)
    public ResponseEntity<?> handleException(DeleteException e) {
        return new ResponseEntity<>("Ошибка удаления данных: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<?> handleException(RegistrationException e) {
        return new ResponseEntity<>("Ошибка регистрации: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

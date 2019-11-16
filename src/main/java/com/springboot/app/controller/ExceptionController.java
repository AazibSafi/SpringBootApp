package com.springboot.app.controller;

import com.springboot.app.constant.ClientMessage;
import com.springboot.app.dto.ClientResponse;
import com.springboot.app.exception.PersonaNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {PersonaNotFoundException.class,EmptyResultDataAccessException.class})
    public ClientResponse handlePersonaNotFoundException(HttpServletRequest request, Exception ex) {
        log.error("Data Not Found");
        log.error("Exception occurred: ",ex);
        return new ClientResponse(ex,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ClientResponse handleMethodArgumentNotValid(HttpServletRequest request, MethodArgumentNotValidException ex) {
        log.error("Input Validation Failed");
        log.error("Exception occurred: ",ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ClientResponse<>(errors,HttpStatus.BAD_REQUEST,ex);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ClientResponse handleNullPointerException(HttpServletRequest request, NullPointerException ex) {
        log.error("Internal Server Error");
        log.error("Exception occurred: ",ex);
        return new ClientResponse(ex,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ClientResponse handleException(HttpServletRequest request, Exception ex) {
        log.error(ClientMessage.SOMETHING_EXPECTED_ERROR_MESSAGE.getMessage());
        log.error("Exception occurred: ",ex);
        return new ClientResponse(ex,HttpStatus.BAD_REQUEST);
    }

}

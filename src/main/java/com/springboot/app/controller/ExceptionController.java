package com.springboot.app.controller;

import com.springboot.app.dto.ClientResponse;
import com.springboot.app.exception.PersonaNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ResponseBody
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {PersonaNotFoundException.class,EmptyResultDataAccessException.class})
    public ClientResponse handlePersonaNotFoundException(HttpServletRequest request, Exception ex) {
        return new ClientResponse(ex,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ClientResponse handleNullPointerException(HttpServletRequest request, NullPointerException ex) {
        return new ClientResponse(ex,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ClientResponse handleException(HttpServletRequest request, Exception ex) {
        return new ClientResponse(ex,HttpStatus.BAD_REQUEST);
    }

}

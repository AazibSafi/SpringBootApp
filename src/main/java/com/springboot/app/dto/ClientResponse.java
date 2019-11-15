package com.springboot.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse<T> {

    private T responseData;
    private String message;
    private int statusCode;
    private Exception exception;

    public ClientResponse(T responseData, String message, int statusCode, Exception exception) {
        this.responseData = responseData;
        this.message = message;
        this.statusCode = statusCode;
        this.exception = exception;
    }

    public ClientResponse(T responseData, String message, int statusCode) {
        this.responseData = responseData;
        this.message = message;
        this.statusCode = statusCode;
    }

    public ClientResponse(T responseData, String message) {
        this.responseData = responseData;
        this.message = message;
        this.statusCode = HttpStatus.OK.value();
    }

    public ClientResponse(String message, Exception exception, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.exception = exception;
    }

}

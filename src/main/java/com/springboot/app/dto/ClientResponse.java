package com.springboot.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse<T> {

    private T responseData;
    private String message;
    private int responseCode;
    private String responseStatus;
    private String exceptionType;

    @JsonIgnore
    private HttpStatus httpStatus;
    @JsonIgnore
    private Exception exception;

    public ClientResponse(T responseData, String message, HttpStatus httpStatus) {
        this.responseData = responseData;
        this.message = message;
        setHttpStatus(httpStatus);
    }

    public ClientResponse(T responseData, HttpStatus httpStatus, Exception ex) {
        this.responseData = responseData;
        setException(ex);
        setHttpStatus(httpStatus);
    }

    public ClientResponse(T responseData, String message) {
        this.responseData = responseData;
        this.message = message;
        setHttpStatus(HttpStatus.OK);
    }

    public ClientResponse(Exception ex, HttpStatus httpStatus) {
        setException(ex);
        setHttpStatus(httpStatus);
    }

    public ClientResponse(String message) {
        this.message = message;
        setHttpStatus(HttpStatus.OK);
    }

    private void setHttpStatus(HttpStatus httpStatus) {
        this.responseCode = httpStatus.value();
        this.responseStatus = httpStatus.getReasonPhrase();
        this.httpStatus = httpStatus;
    }

    private void setException(Exception ex) {
        this.message = ex.getMessage();
        this.exceptionType = ex.getClass().getSimpleName();
        this.exception = ex;
    }

}

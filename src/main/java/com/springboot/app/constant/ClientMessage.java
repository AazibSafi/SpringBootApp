package com.springboot.app.constant;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public enum ClientMessage {

    SOMETHING_EXPECTED_ERROR_MESSAGE("Something Unexpected Happened");

    private String message;

    ClientMessage(String message) {
        this.message = message;
    }

}

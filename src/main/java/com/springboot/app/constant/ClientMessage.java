package com.springboot.app.constant;

import lombok.Getter;

@Getter
public enum ClientMessage {

    SOMETHING_EXPECTED_ERROR_MESSAGE("Something Unexpected Happened"),
    INVALID_HAIR_COLOR("Accepted Hair Colors are [red, yellow, brown, black, white]");

    private String message;

    ClientMessage(String message) {
        this.message = message;
    }

}

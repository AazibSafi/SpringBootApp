package com.springboot.app.constant;

import lombok.Getter;

@Getter
public enum HairColor {

    BLACK("black"),
    RED("red"),
    YELLOW("yellow"),
    BROWN("brown"),
    WHITE("white");

    private String color;

    HairColor(String color) {
        this.color = color;
    }

}

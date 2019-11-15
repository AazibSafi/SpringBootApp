package com.springboot.app.dto;

import com.springboot.app.constant.HairColor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
public class PersonaDto implements Serializable {

    private static final long serialVersionUID = -1937181768548448990L;

    private Integer id;

    private String name;

    private String lastName;

    private String address;

    private Integer phoneNumber;

    @Enumerated(EnumType.STRING)
    private HairColor hairColor;

}

package com.springboot.app.model;

import com.springboot.app.constant.HairColor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String lastName;

    private String address;

    private Integer phoneNumber;

    @Enumerated(EnumType.STRING)
    private HairColor hairColor;

}

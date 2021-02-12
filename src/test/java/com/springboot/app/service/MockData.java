package com.springboot.app.service;

import com.springboot.app.constant.HairColor;
import com.springboot.app.dto.PersonaDto;
import com.springboot.app.model.Persona;

public class MockData {

    public static Persona getPersona() {
        return Persona.builder()
                .id(1)
                .name("John")
                .lastName("Mathew")
                .address("USA")
                .phoneNumber(3214)
                .hairColor(HairColor.brown)
                .build();
    }

    public static PersonaDto getPersonaDto() {
        return PersonaDto.builder()
                .id(1)
                .name("John")
                .lastName("Mathew")
                .address("USA")
                .phoneNumber(3214)
                .hairColor(HairColor.brown)
                .build();
    }

}

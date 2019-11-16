package com.springboot.app.service;

import com.springboot.app.dto.PersonaDto;
import com.springboot.app.exception.PersonaException;

import java.util.List;

public interface PersonaService {

    PersonaDto savePersona(PersonaDto personaDto) throws PersonaException;

    PersonaDto updatePersona(Integer id, PersonaDto personaDto) throws PersonaException;

    List<PersonaDto> getAllPersonas() throws PersonaException;

    PersonaDto getPersonaById(Integer id) throws PersonaException;

    void deletePersonaById(Integer id);

}

package com.springboot.app.service;

import com.springboot.app.dto.PersonaDto;
import com.springboot.app.model.Persona;
import com.springboot.app.repository.PersonaRepo;
import com.springboot.app.util.ConversionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepo personaRepo;

    @Autowired
    private ConversionUtil conversionUtil;

    public PersonaDto savePersona(PersonaDto personaDto) {
        Persona persona = conversionUtil.mapDtoToEntity(personaDto,Persona.class);
        personaRepo.save(persona);
        personaDto.setId(persona.getId());
        return personaDto;
    }

}

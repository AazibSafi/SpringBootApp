package com.springboot.app.service;

import com.springboot.app.dto.PersonaDto;
import com.springboot.app.exception.PersonaException;
import com.springboot.app.exception.PersonaNotFoundException;
import com.springboot.app.model.Persona;
import com.springboot.app.repository.PersonaRepo;
import com.springboot.app.util.ConversionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepo personaRepo;

    @Autowired
    private ConversionUtil conversionUtil;

    @Override
    public PersonaDto savePersona(PersonaDto personaDto) {
        Persona persona = conversionUtil.mapDtoToEntity(personaDto,Persona.class);
        personaRepo.save(persona);
        personaDto.setId(persona.getId());
        return personaDto;
    }

    @Override
    public List<PersonaDto> getAllPersonas() throws PersonaException {
        List<Persona> personas = personaRepo.findAll();
        if(CollectionUtils.isEmpty(personas)) {
            throw new PersonaNotFoundException("No data found");
        }
        return conversionUtil.mapEntityListToDtoList(personas,PersonaDto.class);
    }

    @Override
    public PersonaDto getPersonaById(Integer id) throws PersonaException {
        Persona persona = personaRepo.findById(id)
                .orElseThrow(() -> new PersonaNotFoundException("No data found"));
        return conversionUtil.mapEntityToDto(persona,PersonaDto.class);
    }

    @Override
    public void deletePersonaById(Integer id) {
        personaRepo.deleteById(id);
    }

}

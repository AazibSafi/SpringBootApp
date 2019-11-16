package com.springboot.app.service;

import com.springboot.app.dto.PersonaDto;
import com.springboot.app.exception.PersonaException;
import com.springboot.app.exception.PersonaNotFoundException;
import com.springboot.app.model.Persona;
import com.springboot.app.repository.PersonaRepo;
import com.springboot.app.util.ConversionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepo personaRepo;

    @Autowired
    private ConversionUtil conversionUtil;

    @Override
    public PersonaDto savePersona(PersonaDto personaDto) throws PersonaException {
        if( Objects.nonNull(personaDto.getId()) && personaRepo.findById(personaDto.getId()).isPresent() ) {
            throw new PersonaException("Persona already exist");
        }

        Persona persona = conversionUtil.mapDtoToEntity(personaDto,Persona.class);
        personaRepo.save(persona);
        log.info("Persona Info is saved with id {}",persona.getId());
        personaDto.setId(persona.getId());
        return personaDto;
    }

    @Override
    public PersonaDto updatePersona(PersonaDto personaDto) throws PersonaException {
        if( Objects.isNull(personaDto.getId()) ) {
            throw new PersonaException("Persona id cannot be null");
        }

        Persona persona = personaRepo.findById(personaDto.getId())
                .orElseThrow(() -> new PersonaException("Persona does not exist with id "+personaDto.getId()));

        conversionUtil.mapSourceModelToDestinationModel(personaDto,persona);
        personaRepo.save(persona);
        log.info("Persona Info is updated");
        return personaDto;
    }

    @Override
    public List<PersonaDto> getAllPersonas() throws PersonaException {
        List<Persona> personas = personaRepo.findAll();
        if(CollectionUtils.isEmpty(personas)) {
            throw new PersonaNotFoundException("No data found");
        }
        log.info("All Persona Information are retrieved");
        return conversionUtil.mapEntityListToDtoList(personas,PersonaDto.class);
    }

    @Override
    public PersonaDto getPersonaById(Integer id) throws PersonaException {
        Persona persona = personaRepo.findById(id)
                .orElseThrow(() -> new PersonaNotFoundException("No data found"));
        log.info("Persona Info with id {} is retrieved",persona.getId());
        return conversionUtil.mapEntityToDto(persona,PersonaDto.class);
    }

    @Override
    public void deletePersonaById(Integer id) {
        personaRepo.deleteById(id);
        log.info("Persona Info with id {} is deleted",id);
    }

}

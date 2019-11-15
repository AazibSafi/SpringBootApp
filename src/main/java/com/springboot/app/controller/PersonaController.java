package com.springboot.app.controller;

import com.springboot.app.dto.ClientResponse;
import com.springboot.app.dto.PersonaDto;
import com.springboot.app.exception.PersonaException;
import com.springboot.app.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ClientResponse savePersona(@RequestBody PersonaDto personaDto) {
        personaDto = personaService.savePersona(personaDto);
        return new ClientResponse<>(personaDto,"Data Saved");
    }

    @GetMapping
    public ClientResponse getPersonas() throws PersonaException {
        List<PersonaDto> personaDtos = personaService.getAllPersonas();
        return new ClientResponse<>(personaDtos,"Data Retrieved");
    }

    @GetMapping("{id}")
    public ClientResponse getPersonaById(@PathVariable("id") Integer id) throws PersonaException {
        PersonaDto personaDto = personaService.getPersonaById(id);
        return new ClientResponse<>(personaDto,"Data Retrieved");
    }

    @DeleteMapping("{id}")
    public ClientResponse deletePersonaById(@PathVariable("id") Integer id) {
        personaService.deletePersonaById(id);
        return new ClientResponse<>("Data Deleted");
    }


}

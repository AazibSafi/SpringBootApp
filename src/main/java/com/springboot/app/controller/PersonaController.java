package com.springboot.app.controller;

import com.springboot.app.dto.ClientResponse;
import com.springboot.app.dto.PersonaDto;
import com.springboot.app.exception.PersonaException;
import com.springboot.app.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ClientResponse savePersona(@RequestBody PersonaDto personaDto) throws PersonaException {
        personaDto = personaService.savePersona(personaDto);
        return new ClientResponse<>(personaDto,"Data Saved");
    }

}

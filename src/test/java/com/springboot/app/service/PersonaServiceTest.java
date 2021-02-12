package com.springboot.app.service;

import com.springboot.app.BaseTest;
import com.springboot.app.dto.PersonaDto;
import com.springboot.app.exception.PersonaException;
import com.springboot.app.exception.PersonaNotFoundException;
import com.springboot.app.repository.PersonaRepo;
import com.springboot.app.util.ConversionUtil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class PersonaServiceTest extends BaseTest {

    @InjectMocks
    private PersonaServiceImpl personaService;

    @Mock
    private PersonaRepo personaRepo;

    @Mock
    private ConversionUtil conversionUtil;

    @Test
    public void testSavePersona() throws PersonaException {
        when(personaRepo.findById(anyInt())).thenReturn(Optional.empty());
        when(personaRepo.save(any())).thenReturn(MockData.getPersona());
        when(conversionUtil.mapDtoToEntity(any(PersonaDto.class), any())).thenReturn(MockData.getPersona());
        PersonaDto personaDto = personaService.savePersona(MockData.getPersonaDto());
        Assert.assertEquals(MockData.getPersona().getId(), personaDto.getId());
    }

    @Test(expected = PersonaException.class)
    public void testSavePersonaThrowsPersonaException() throws PersonaException {
        when(personaRepo.findById(anyInt())).thenReturn(Optional.of(MockData.getPersona()));
        personaService.savePersona(MockData.getPersonaDto());
    }

    @Test
    public void testUpdatePersona() throws PersonaException {
        when(personaRepo.findById(anyInt())).thenReturn(Optional.of(MockData.getPersona()));
        doNothing().when(conversionUtil).mapSourceModelToDestinationModel(any(), any());
        when(personaRepo.save(any())).thenReturn(MockData.getPersona());
        PersonaDto personaDto = personaService.updatePersona(1, MockData.getPersonaDto());
        Assert.assertNotNull(personaDto);
    }

    @Test(expected = PersonaException.class)
    public void testUpdatePersonaThrowsPersonaException() throws PersonaException {
        personaService.updatePersona(null, MockData.getPersonaDto());
    }

    @Test(expected = PersonaNotFoundException.class)
    public void testUpdatePersonaThrowsPersonaNotFoundException() throws PersonaException {
        when(personaRepo.findById(anyInt())).thenReturn(Optional.empty());
        personaService.updatePersona(1, MockData.getPersonaDto());
    }

    @Test
    public void testGetAllPersonas() throws PersonaException {
        when(personaRepo.findAll()).thenReturn(Arrays.asList(MockData.getPersona()));
        when(conversionUtil.mapEntityListToDtoList(any(), any())).thenReturn(Arrays.asList(MockData.getPersonaDto()));
        personaService.getAllPersonas();
    }

    @Test(expected = PersonaNotFoundException.class)
    public void testGetAllPersonasThrowsPersonaNotFoundException() throws PersonaException {
        when(personaRepo.findAll()).thenReturn(Collections.emptyList());
        personaService.getAllPersonas();
    }

    @Test
    public void testGetPersonaById() throws PersonaException {
        when(personaRepo.findById(anyInt())).thenReturn(Optional.of(MockData.getPersona()));
        when(conversionUtil.mapEntityToDto(any(), any())).thenReturn(MockData.getPersonaDto());
        PersonaDto personaDto = personaService.getPersonaById(1);
        Assert.assertNotNull(personaDto);
    }

    @Test(expected = PersonaNotFoundException.class)
    public void testGetPersonaByIdThrowsPersonaNotFoundException() throws PersonaException {
        when(personaRepo.findById(anyInt())).thenReturn(Optional.empty());
        personaService.getPersonaById(1);
    }

    @Test
    public void testDeletePersonaById() throws PersonaException {
        doNothing().when(personaRepo).deleteById(anyInt());
        personaService.deletePersonaById(1);
        Mockito.verify(personaRepo, times(1)).deleteById(anyInt());
    }

}

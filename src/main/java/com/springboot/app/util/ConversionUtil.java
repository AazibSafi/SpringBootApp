package com.springboot.app.util;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConversionUtil {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param entityList list of entities that needs to be mapped
     * @param outCLass   class of result list element
     * @param <D>        type of objects in result list
     * @param <T>        type of entity in <code>entityList</code>
     * @return list of mapped object with <code><D></code> type.
     */
    public <D, T> List<D> mapEntityListToDtoList(Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> mapEntityToDto(entity, outCLass))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to convert Entity To DTO.
     *
     * @param entityObject source Object which will be convert into DTO.
     * @param dtoObject    target object.
     * @param <T>
     * @return DTO Object.
     */
    public <T> T mapEntityToDto(Object entityObject, Class<T> dtoObject) {
        return modelMapper.map(entityObject, dtoObject);
    }

    /**
     * This method is used to convert DTO to Entity.
     *
     * @param dtoObject    source Object which will be convert into Entity.
     * @param entityObject target Object.
     * @param <T>
     * @return Entity Object.
     */
    public <T> T mapDtoToEntity(Object dtoObject, Class<T> entityObject) {
        return modelMapper.map(dtoObject, entityObject);
    }

    public void mapSourceModelToDestinationModel(Object source, Object destination) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(source, destination);
    }

}

package com.bispo.firstprojectrestapi.mapper;

import com.bispo.firstprojectrestapi.entity.PersonEntity;
import com.bispo.firstprojectrestapi.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    /**
     * Maps a PersonEntity to a Person model.
     * @param entity the PersonEntity to be mapped
     * @return the corresponding Person model
     */
    Person toModel(PersonEntity entity);

    /**
     * Maps a Person model to a PersonEntity.
     * @param model the Person model to be mapped
     * @return the corresponding PersonEntity
     */
    PersonEntity toEntity(Person model);

}

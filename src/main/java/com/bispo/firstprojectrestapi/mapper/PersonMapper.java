package com.bispo.firstprojectrestapi.mapper;

import com.bispo.firstprojectrestapi.dto.PersonDTO;
import com.bispo.firstprojectrestapi.dto.PersonResponseDTO;
import com.bispo.firstprojectrestapi.entity.PersonEntity;
import com.bispo.firstprojectrestapi.model.Person;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
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

    /**
     * Maps a PersonDTO to a Person model.
     * @param dto the PersonDTO to be mapped
     * @return the corresponding Person model
     */
    Person toModel(PersonDTO dto);

    /**
     * Maps a Person model to a PersonDTO.
     * @param model the Person model to be mapped
     * @return the corresponding PersonDTO
     */
    PersonDTO toDTO(Person model);

    /**
     * Maps a Person model to a PersonResponseDTO.
     * @param model the Person model to be mapped
     * @return the corresponding PersonResponseDTO
     */
    PersonResponseDTO toResponseDTO(Person model);

    /**
     * Maps a list of Person models to a list of PersonDTOs.
     * @param models the list of Person models to be mapped
     * @return the corresponding list of PersonDTOs
     */
    List<PersonResponseDTO> toResponseDTOList(List<Person> models);

    /**
     * Maps a Person model to a PersonEntity for update operations.
     * Ignores the 'id' field to prevent overwriting the existing identifier.
     * @param person the Person model containing updated information
     * @param entity the existing PersonEntity to be updated
     */
    @Mapping(target = "id", ignore = true)
    @BeanMapping(
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
    )
    void toEntityForUpdate(Person person, @MappingTarget PersonEntity entity);

}

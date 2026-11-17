package com.bispo.firstprojectrestapi.service.impl;

import com.bispo.firstprojectrestapi.entity.PersonEntity;
import com.bispo.firstprojectrestapi.mapper.PersonMapper;
import com.bispo.firstprojectrestapi.model.Person;
import com.bispo.firstprojectrestapi.repository.PersonRepository;
import com.bispo.firstprojectrestapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {

        List<PersonEntity> personEntities = personRepository.findAll();
        return personEntities.stream()
                .map(PersonMapper.INSTANCE::toModel)
                .toList();
    }

}

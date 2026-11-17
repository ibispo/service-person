package com.bispo.firstprojectrestapi.service.impl;

import com.bispo.firstprojectrestapi.entity.PersonEntity;
import com.bispo.firstprojectrestapi.exception.PersonNotFoundException;
import com.bispo.firstprojectrestapi.mapper.PersonMapper;
import com.bispo.firstprojectrestapi.model.Person;
import com.bispo.firstprojectrestapi.repository.PersonRepository;
import com.bispo.firstprojectrestapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public List<Person> getAllPeople() {
        List<PersonEntity> personEntities = personRepository.findAll();
        return personEntities.stream()
                .map(PersonMapper.INSTANCE::toModel)
                .toList();
    }

    @Override
    public Person save(Person person) {
        PersonEntity personEntity = PersonMapper.INSTANCE.toEntity(person);
        PersonEntity personEntitySaved = personRepository.save(personEntity);
        return PersonMapper.INSTANCE.toModel(personEntitySaved);
    }

    @Override
    public Person getPersonById(UUID id) {
        Optional<PersonEntity> personEntityOptional = personRepository.findById(id);
        PersonEntity personEntity = personEntityOptional
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));
        return PersonMapper.INSTANCE.toModel(personEntity);
    }

}

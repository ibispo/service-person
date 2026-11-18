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

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    /**
     * Retrieves a list of all persons.
     * @return a list of Person objects
     */
    @Override
    public List<Person> getAllPeople() {
        List<PersonEntity> personEntities = personRepository.findAll();
        return personEntities.stream()
                .map(PersonMapper.INSTANCE::toModel)
                .toList();
    }

    /**
     * Saves a person to the database.
     * @param person the Person object to be saved
     * @return the saved Person object
     */
    @Override
    public Person save(Person person) {
        PersonEntity personEntity = PersonMapper.INSTANCE.toEntity(person);
        PersonEntity personEntitySaved = personRepository.save(personEntity);
        return PersonMapper.INSTANCE.toModel(personEntitySaved);
    }

    /**
     * Retrieves a person by their unique identifier.
     * @param id the UUID of the person to be retrieved
     * @return the Person object corresponding to the given id
     * @throws PersonNotFoundException
     */
    @Override
    public Person getPersonById(UUID id) throws PersonNotFoundException {
        Optional<PersonEntity> personEntityOptional = personRepository.findById(id);
        PersonEntity personEntity = personEntityOptional
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));
        return PersonMapper.INSTANCE.toModel(personEntity);
    }

    /**
     * Deletes a person by their unique identifier.
     * @param id the UUID of the person to be deleted
     * @throws PersonNotFoundException
     */
    @Override
    public void deletePerson(UUID id) throws PersonNotFoundException {
        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));
        personRepository.delete(personEntity);
    }

    @Override
    public Person update(UUID id, Person person) throws PersonNotFoundException {
        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));

        // TODO Update the personEntity with the new values from the 'person' parameter
        // For example: personEntity.setName(person.getName());
        // ... update other fields as needed

        // person.match(personEntity); // Assuming you have a method to update the entity with the model's values

        PersonEntity personEntityUpdated = personRepository.save(personEntity);
        return PersonMapper.INSTANCE.toModel(personEntityUpdated);
    }

}

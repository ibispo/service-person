package com.bispo.firstprojectrestapi.service;

import com.bispo.firstprojectrestapi.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    /**
     * Retrieves a list of all persons.
     * @return a list of Person objects
     */
    List<Person> getAllPeople();

    /**
     * Saves a person to the database.
     * @param person the Person object to be saved
     * @return the saved Person object
     */
    Person save(Person person);

    Person getPersonById(UUID id);
}

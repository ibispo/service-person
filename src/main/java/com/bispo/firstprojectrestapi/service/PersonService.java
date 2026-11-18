package com.bispo.firstprojectrestapi.service;

import com.bispo.firstprojectrestapi.exception.PersonNotFoundException;
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

    /**
     * Retrieves a person by their unique identifier.
     * @param id the UUID of the person to be retrieved
     * @return the Person object corresponding to the given id
     * @throws PersonNotFoundException if no person is found with the given id
     */
    Person getPersonById(UUID id) throws PersonNotFoundException;

    /**
     * Deletes a person by their unique identifier.
     * @param id the UUID of the person to be deleted
     * @throws PersonNotFoundException if no person is found with the given id
     */
    void deletePerson(UUID id) throws PersonNotFoundException;

    /**
     * Updates a person by their unique identifier.
     * @param id the UUID of the person to be updated
     * @param person the Person object containing the updated information
     * @return the updated Person object
     * @throws PersonNotFoundException if no person is found with the given id
     */
    Person update(UUID id, Person person) throws PersonNotFoundException;

}

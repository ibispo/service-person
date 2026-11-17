package com.bispo.firstprojectrestapi.service;

import com.bispo.firstprojectrestapi.model.Person;

import java.util.List;

public interface PersonService {

    /**
     * Retrieves a list of all persons.
     * @return a list of Person objects
     */
    List<Person> getAllPersons();

}

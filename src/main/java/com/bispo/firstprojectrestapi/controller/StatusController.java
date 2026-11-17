package com.bispo.firstprojectrestapi.controller;

import com.bispo.firstprojectrestapi.dto.PersonDTO;
import com.bispo.firstprojectrestapi.mapper.PersonMapper;
import com.bispo.firstprojectrestapi.model.Person;
import com.bispo.firstprojectrestapi.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StatusController {

    private final PersonService personService;

    @GetMapping(path = "/people")
    public List<PersonDTO> findPeople() {
        return PersonMapper.INSTANCE.toDTOList(personService.getAllPeople());
    }

    @GetMapping(path = "/person/{id}")
    public PersonDTO findById(@PathVariable UUID id) {
        Person people = personService.getPersonById(id);
        return PersonMapper.INSTANCE.toDTO(people);
    }

    @PostMapping(path = "/person")
    public PersonDTO createPerson(@Valid @RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toModel(personDTO);
        Person personSaved = personService.save(person);
        return PersonMapper.INSTANCE.toDTO(personSaved);
    }


}

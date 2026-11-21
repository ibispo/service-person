package com.bispo.firstprojectrestapi.controller;

import com.bispo.firstprojectrestapi.dto.PersonDTO;
import com.bispo.firstprojectrestapi.dto.PersonResponseDTO;
import com.bispo.firstprojectrestapi.mapper.PersonMapper;
import com.bispo.firstprojectrestapi.model.Person;
import com.bispo.firstprojectrestapi.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Description("Controller responsible for handling HTTP requests related to Person entities. " +
        "Provides endpoints for creating, retrieving, updating, and deleting Person records.")
public class PersonController {

    private final PersonService personService;

    @GetMapping(path = "/people")
    public List<PersonResponseDTO> findPeople() {
        return PersonMapper.INSTANCE.toResponseDTOList(personService.getAllPeople());
    }

    @GetMapping(path = "/person/{id}")
    public PersonResponseDTO findById(@PathVariable UUID id) {
        Person person = personService.getPersonById(id);
        return PersonMapper.INSTANCE.toResponseDTO(person);
    }

    @PostMapping(path = "/person")
    public ResponseEntity<PersonResponseDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toModel(personDTO);
        Person personSaved = personService.save(person);
        return ResponseEntity.ok().body(PersonMapper.INSTANCE.toResponseDTO(personSaved));
    }

    @DeleteMapping(path = "/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/person/{id}")
    public ResponseEntity<PersonResponseDTO> updatePerson(
            @PathVariable UUID id,
            @Valid @RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toModel(personDTO);
        Person personSaved = personService.update(id, person);
        return ResponseEntity.ok().body(PersonMapper.INSTANCE.toResponseDTO(personSaved));
    }

    @PatchMapping(path = "/person/{id}")
    public ResponseEntity<PersonResponseDTO> patchPerson(
            @PathVariable UUID id,
            @RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toModel(personDTO);
        Person personSaved = personService.update(id, person);
        return ResponseEntity.ok().body(PersonMapper.INSTANCE.toResponseDTO(personSaved));
    }

}

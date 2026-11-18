package com.bispo.firstprojectrestapi.controller;

import com.bispo.firstprojectrestapi.dto.PersonDTO;
import com.bispo.firstprojectrestapi.mapper.PersonMapper;
import com.bispo.firstprojectrestapi.model.Person;
import com.bispo.firstprojectrestapi.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Person person = personService.getPersonById(id);
        return PersonMapper.INSTANCE.toDTO(person);
    }

    @PostMapping(path = "/person")
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toModel(personDTO);
        Person personSaved = personService.save(person);
        return ResponseEntity.ok().body(PersonMapper.INSTANCE.toDTO(personSaved));
    }

    @DeleteMapping(path = "/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/person/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable UUID id, @Valid @RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toModel(personDTO);
        Person personSaved = personService.update(id, person);
        return ResponseEntity.ok().body(PersonMapper.INSTANCE.toDTO(personSaved));
    }

    @PatchMapping(path = "/person/{id}")
    public ResponseEntity<PersonDTO> patchPerson(@PathVariable UUID id, @RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toModel(personDTO);
        Person personSaved = personService.update(id, person);
        return ResponseEntity.ok().body(PersonMapper.INSTANCE.toDTO(personSaved));
    }

}

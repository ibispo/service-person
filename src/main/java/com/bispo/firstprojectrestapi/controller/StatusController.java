package com.bispo.firstprojectrestapi.controller;

import com.bispo.firstprojectrestapi.dto.PersonDTO;
import com.bispo.firstprojectrestapi.mapper.PersonMapper;
import com.bispo.firstprojectrestapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StatusController {

    private final PersonService personService;

    @GetMapping(path = "/people")
    public List<PersonDTO> findPeople() {
        return PersonMapper.INSTANCE.toDTOList(personService.getAllPeople());
    }

}

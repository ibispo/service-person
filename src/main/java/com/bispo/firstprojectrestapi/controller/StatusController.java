package com.bispo.firstprojectrestapi.controller;

import com.bispo.firstprojectrestapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api")
@AllArgsConstructor
public class StatusController {

    private final PersonService personService;

    @GetMapping(path = "/persons")
    public String check() {
        return "online";
    }

}

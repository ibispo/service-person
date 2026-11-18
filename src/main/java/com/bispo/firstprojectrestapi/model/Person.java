package com.bispo.firstprojectrestapi.model;

import com.bispo.firstprojectrestapi.enums.GenderEnum;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.UUID;

@Builder(toBuilder = true)
@Getter
public class Person {

    private UUID id;
    private String name;
    private GenderEnum gender;
    private String address;
    private String zipcode;
    private String city;
    private String phone;
    private String email;
    private LocalDate birthDate;

}

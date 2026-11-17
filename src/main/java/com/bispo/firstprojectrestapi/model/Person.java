package com.bispo.firstprojectrestapi.model;

import com.bispo.firstprojectrestapi.enums.GenderEnum;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import jakarta.validation.constraints.Email;

import java.util.UUID;

@Builder
@Getter
public class Person {

    private UUID id;

    @Valid
    private String name;

    private GenderEnum gender;

    private String address;

    @Valid
    private String zipcode;

    private String city;

    @Valid
    private String phone;

    @Email
    private String email;

}

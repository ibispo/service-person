package com.bispo.firstprojectrestapi.dto;

import com.bispo.firstprojectrestapi.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.UUID;

public record PersonDTO(

        @Pattern(regexp = "\\p{L}+(?: \\p{L}+)*", message = "Name must contain only alphabetic characters and spaces")
        String name,

        GenderEnum gender,
        String address,
        String zipcode,
        String city,
        String phone,

        @Email
        String email,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate
) {

}
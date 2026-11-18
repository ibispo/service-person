package com.bispo.firstprojectrestapi.model;

import com.bispo.firstprojectrestapi.enums.GenderEnum;

import java.time.LocalDate;
import java.util.UUID;

public record Person(
    UUID id,
    String name,
    GenderEnum gender,
    String address,
    String zipcode,
    String city,
    String phone,
    String email,
    LocalDate birthDate
) {
}

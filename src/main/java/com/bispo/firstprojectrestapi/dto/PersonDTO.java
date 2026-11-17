package com.bispo.firstprojectrestapi.dto;

import java.util.UUID;

public record PersonDTO(
        UUID id,
        String name,
        String gender,
        String address,
        String zipcode,
        String city,
        String phone,
        String email
) {

}
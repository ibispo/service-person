package com.bispo.firstprojectrestapi.dto;

import com.bispo.firstprojectrestapi.enums.GenderEnum;

import java.time.LocalDate;
import java.time.Period;

public record PersonResponseDTO(
    String name,
    GenderEnum gender,
    String address,
    String zipcode,
    String city,
    String phone,
    String email,
    LocalDate birthDate
) {

    /**
     * Calculates the age based on the birthDate.
     * @return the age in years, or null if birthDate is null
     */
    public Integer getAge() {
        if (birthDate == null) {
            return null;
        }
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

}

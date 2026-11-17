package com.bispo.firstprojectrestapi.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public class Person {

    private UUID id;
    private String name;
    private String gender;
    private String address;
    private String zipcode;
    private String city;
    private String phone;
    private String email;

}

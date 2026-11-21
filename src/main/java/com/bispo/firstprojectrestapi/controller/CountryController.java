package com.bispo.firstprojectrestapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CountryController {

    private final String countryApiToken;
    private final RestClient restClient;

    public CountryController(
            @Value("${country.api.url}") String countryApiUrl,
            @Value("${country.api.token}") String countryApiToken) {
        this.countryApiToken = countryApiToken;
        this.restClient = RestClient.builder().baseUrl(countryApiUrl).build();
    }

    @GetMapping("/countries")
    public String getCountryInfo() {
        if (countryApiToken.isBlank()) {
            throw new IllegalStateException("COUNTRY_API_TOKEN environment variable is not configured");
        }

        return restClient.get()
                .uri("/countries/v5?q=canada")
                .header("Authorization", "Bearer " + countryApiToken)
                .retrieve()
                .body(String.class);
    }
}

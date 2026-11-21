package com.bispo.firstprojectrestapi.controller;

import com.bispo.firstprojectrestapi.dto.CountryResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestClient;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

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

    @Cacheable("countries")
    @GetMapping("/countries")
    public List<CountryResponseDTO> getCountryInfo() {
        if (countryApiToken.isBlank()) {
            throw new IllegalStateException("COUNTRY_API_TOKEN environment variable is not configured");
        }

        JsonNode response = restClient.get()
                .uri("/countries/v5?q=canada&response_fields=codes.alpha_2,names.common")
                .header("Authorization", "Bearer " + countryApiToken)
                .retrieve()
                .body(JsonNode.class);

        JsonNode countries = response == null ? null : response.path("data").path("objects");
        if (countries == null || !countries.isArray()) {
            throw new IllegalStateException("Country API response does not contain a data.objects array");
        }

        List<CountryResponseDTO> result = new ArrayList<>();
        for (JsonNode country : countries) {
            String code = country.path("codes").path("alpha_2").asString(null);
            String name = country.path("names").path("common").asString(null);
            if (code == null || name == null) {
                throw new IllegalStateException("Country API response is missing a country code or name");
            }
            result.add(new CountryResponseDTO(code, name));
        }
        return result;
    }
}

package io.com.resume.country;

public record CountryDto(
        Long id,
        String iso,
        String name,
        String nicename,
        String iso3,
        Integer numcode,
        Integer phonecode) {


}

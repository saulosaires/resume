package io.com.resume.country;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto toDto(Country country);
}

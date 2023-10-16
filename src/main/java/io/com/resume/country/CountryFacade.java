package io.com.resume.country;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryFacade {

    private final CountryService countryService;
    private final CountryMapper mapper;

    public CountryFacade(CountryService countryService, CountryMapper mapper) {
        this.countryService = countryService;
        this.mapper = mapper;
    }

    public List<CountryDto> findAll() {
        return countryService.findAll().stream().map(mapper::toDto).toList();
    }

}

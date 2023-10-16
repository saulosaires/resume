package io.com.resume.country;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public Country findById(Long id) {
        Country country = repository.findById(id).orElse(null);

        return country;
    }

    public List<Country> findAll() {
        return repository.findAll();
    }
}

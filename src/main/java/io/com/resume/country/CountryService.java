package io.com.resume.country;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CountryService {

    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public Optional<Country> findById(Long id) {
        Optional<Country> country = repository.findById(id);
        log.info("CountryService.findById {}  present{}", id, country.isPresent());
        return country;
    }

    public List<Country> findAll() {
        return repository.findAll();
    }
}

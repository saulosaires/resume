package io.com.resume.languages.language;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageFacade {

    private final LanguageRepository repository;
    private final LanguageMapper mapper;

    public LanguageFacade(LanguageRepository repository, LanguageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<LanguageDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}

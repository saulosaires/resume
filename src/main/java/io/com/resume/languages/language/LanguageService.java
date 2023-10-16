package io.com.resume.languages.language;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository repository;

    public Language findById(Long id) {
        Language language = repository.findById(id).orElse(null);

        return language;
    }
}
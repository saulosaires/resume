package io.com.resume.languages.user_language;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLanguageService {

    private final UserLanguageRepository repository;

    List<UserLanguage> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    UserLanguage create(UserLanguage userLanguage) {
        return repository.save(userLanguage);
    }
}

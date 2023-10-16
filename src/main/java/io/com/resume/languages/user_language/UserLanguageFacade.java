package io.com.resume.languages.user_language;

import io.com.resume.languages.language.Language;
import io.com.resume.languages.language.LanguageService;
import io.com.resume.user.User;
import io.com.resume.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLanguageFacade {

    private final UserService userService;
    private final LanguageService languageService;
    private final UserLanguageService service;
    private final UserLanguageMapper mapper;

    public List<UserLanguageDto> findByUserId(Long userId) {
        return service.findByUserId(userId).stream().map(mapper::toDto).toList();
    }

    public UserLanguageDto create(Long userId, Long languageId, int level) {

        User user = userService.findById(userId);
        Language language = languageService.findById(languageId);

        UserLanguage userLanguage = new UserLanguage();
        userLanguage.setUser(user);
        userLanguage.setLanguage(language);
        userLanguage.setLevel(level);

        return mapper.toDto(service.create(userLanguage));

    }
}

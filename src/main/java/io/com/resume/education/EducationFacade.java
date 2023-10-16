package io.com.resume.education;

import io.com.resume.languages.language.LanguageService;
import io.com.resume.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationFacade {

    private final UserService userService;
    private final LanguageService languageService;
    private final EducationService service;
    private final EducationMapper mapper;

    public EducationDto create(EducationDto educationDto) {

        Education education = mapper.fromDto(educationDto);

        education.setUser(userService.findById(educationDto.user().id()));
        education.setLanguage(languageService.findById(educationDto.language().id()));

        Education educationCreated = service.create(education);

        return mapper.toDto(educationCreated);
    }

    public List<EducationDto> findByUserId(Long userId) {
        return service.findByUserId(userId).stream().map(mapper::toDto).toList();
    }
}

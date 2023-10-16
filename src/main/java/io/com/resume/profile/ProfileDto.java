package io.com.resume.profile;

import io.com.resume.country.CountryDto;
import io.com.resume.education.EducationDto;
import io.com.resume.experience.ExperienceDto;
import io.com.resume.languages.user_language.UserLanguageDto;
import io.com.resume.link.dto.LinkDto;

import java.time.LocalDate;
import java.util.List;

public record ProfileDto(
        Long userId,
        String name,
        String summary,
        String email,
        String telephone,
        String website,
        String instantMessaging,
        LocalDate birthDate,
        List<LinkDto> links,
        List<EducationDto> education,
        List<ExperienceDto> experiences,
        List<UserLanguageDto> languages,
        CountryDto country) {
}

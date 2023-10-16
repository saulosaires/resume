package io.com.resume.experience;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.com.resume.country.CountryDto;
import io.com.resume.languages.language.LanguageDto;
import io.com.resume.skill.dto.SkillDto;
import io.com.resume.user.UserDto;

import java.time.LocalDate;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExperienceDto(
        String title,
        String companyName,
        LocalDate startDate,
        LocalDate endDate,
        String description,
        String url,
        CountryDto country,
        List<SkillDto> skills,
        LanguageDto language,
        @JsonIgnore UserDto user) {
}

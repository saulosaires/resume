package io.com.resume.education;

import io.com.resume.languages.language.LanguageDto;
import io.com.resume.user.UserDto;

import java.time.LocalDate;


public record EducationDto(Long id,
                           String school,
                           String degree,
                           String city,
                           String description,
                           LocalDate startDate,
                           LocalDate endDate,
                           UserDto user,
                           LanguageDto language) {
}

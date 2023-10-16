package io.com.resume.languages.user_language;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.com.resume.languages.language.LanguageDto;
import io.com.resume.user.UserDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserLanguageDto(Long id, UserDto user, LanguageDto language, int level) {
}

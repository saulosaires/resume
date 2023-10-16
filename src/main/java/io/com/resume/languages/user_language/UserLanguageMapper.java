package io.com.resume.languages.user_language;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserLanguageMapper {

    UserLanguageDto toDto(UserLanguage language);

    UserLanguage fromDto(UserLanguageDto languageDto);

}

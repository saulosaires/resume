package io.com.resume.languages.language;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

    LanguageDto toDto(Language language);
}

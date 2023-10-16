package io.com.resume.profile;

import io.com.resume.education.EducationDto;
import io.com.resume.education.EducationMapper;
import io.com.resume.experience.ExperienceDto;
import io.com.resume.experience.ExperienceMapper;
import io.com.resume.languages.user_language.UserLanguageDto;
import io.com.resume.languages.user_language.UserLanguageMapper;
import io.com.resume.link.LinkMapper;
import io.com.resume.link.dto.LinkDto;
import io.com.resume.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {LinkMapper.class, EducationMapper.class, ExperienceMapper.class, UserLanguageMapper.class})
public interface ProfileMapper {


    @Mapping(source = "user.id", target = "userId")
    ProfileDto toDto(UserDto user, List<LinkDto> links, List<EducationDto> education, List<ExperienceDto> experiences,
                     List<UserLanguageDto> languages);
}

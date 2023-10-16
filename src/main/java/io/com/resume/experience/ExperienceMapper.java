package io.com.resume.experience;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExperienceMapper {

    ExperienceDto toDto(Experience experience);

    Experience fromDto(ExperienceDto experienceDto);
}

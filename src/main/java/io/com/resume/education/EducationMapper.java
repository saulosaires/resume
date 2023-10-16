package io.com.resume.education;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EducationMapper {

    EducationDto toDto(Education education);

    Education fromDto(EducationDto educationDto);
}

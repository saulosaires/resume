package io.com.resume.link;

import io.com.resume.link.dto.LinkDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LinkMapper {

    LinkDto toDto(Link link);

    Link fromDto(LinkDto linkDto);
}

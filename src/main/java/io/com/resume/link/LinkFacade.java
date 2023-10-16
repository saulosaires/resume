package io.com.resume.link;

import io.com.resume.link.dto.LinkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkFacade {

    private final LinkService linkService;
    private final LinkMapper linkMapper;

    public List<LinkDto> findByUser(Long userId) {
        return linkService.findByUserId(userId).stream().map(linkMapper::toDto).toList();
    }

    public LinkDto create(LinkDto linkDto) {

        Link link = linkMapper.fromDto(linkDto);

        Link linkCreated = linkService.create(link);

        return linkMapper.toDto(linkCreated);
    }

}

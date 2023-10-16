package io.com.resume.link;

import io.com.resume.link.dto.LinkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("link/")
@RequiredArgsConstructor
public class LinkController {

    private final LinkFacade facade;

    @PostMapping
    public LinkDto create(@RequestBody LinkDto linkDto) {
        return facade.create(linkDto);
    }

}

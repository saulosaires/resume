package io.com.resume.experience;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("experience/")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceFacade facade;

    @PostMapping
    public ExperienceDto create(@RequestBody ExperienceDto experienceDto) {
        return facade.create(experienceDto);
    }
}

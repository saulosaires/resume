package io.com.resume.experience;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("experience/")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceFacade facade;

    @PostMapping
    public ExperienceDto create(@RequestBody ExperienceDto experienceDto) {
        return facade.create(experienceDto);
    }

    @GetMapping("{userId}")
    public List<ExperienceDto> findByUserId(@PathVariable("userId") Long userId) {
        return facade.findByUserId(userId);
    }

}

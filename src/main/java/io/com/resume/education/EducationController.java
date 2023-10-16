package io.com.resume.education;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("education/")
public class EducationController {

    private final EducationFacade facade;

    public EducationController(EducationFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public EducationDto create(@RequestBody EducationDto educationDto) {
        return facade.create(educationDto);
    }
}

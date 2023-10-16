package io.com.resume.languages.language;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("language/")
public class LanguageController {

    private final LanguageFacade facade;

    public LanguageController(LanguageFacade facade) {
        this.facade = facade;
    }

    @GetMapping("all")
    public List<LanguageDto> findAll() {
        return facade.findAll();

    }
}

package io.com.resume.template;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("template/")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateFacade templateFacade;


    @GetMapping("all")
    public void findAll() {
        templateFacade.getTemplate();
    }

}

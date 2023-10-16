package io.com.resume.country;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("country/")
public class CountryController {

    private final CountryFacade facade;

    public CountryController(CountryFacade facade) {
        this.facade = facade;
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<CountryDto> findAll() {
        return facade.findAll();
    }
}

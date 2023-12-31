package io.com.resume.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("profile/")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileFacade profileFacade;


    @GetMapping("{id}")
    public ProfileDto findById(@PathVariable("id") Long id) {
        return profileFacade.findById(id);
    }


}



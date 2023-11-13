package io.com.resume.user;

import io.com.resume.languages.user_language.UserLanguageDto;
import io.com.resume.languages.user_language.UserLanguageFacade;
import io.com.resume.link.LinkFacade;
import io.com.resume.link.dto.LinkDto;
import io.com.resume.user.exception.InvalidUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("user/")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;
    private final LinkFacade linkFacade;
    private final UserLanguageFacade userLanguageFacade;

    @PutMapping()
    public UserDto update(@RequestBody UserDto userDto) throws InvalidUserException {
        return userFacade.update(userDto);
    }

    @PostMapping("sign-in")
    public UserDto signIn(@RequestBody UserDto userDto) {
        return userFacade.signIn(userDto);
    }

    @GetMapping("{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        return userFacade.findById(id);
    }

    @GetMapping("{id}/link")
    public List<LinkDto> findByUser(@PathVariable("id") Long userId) {
        return linkFacade.findByUser(userId);
    }

    @GetMapping("{id}/language/{languageId}/level/{level}")
    public UserLanguageDto addUserLanguage(@PathVariable("id") Long userId, @PathVariable("languageId") Long languageId,
                                           @PathVariable("level") int level) {
        return userLanguageFacade.create(userId, languageId, level);
    }

}



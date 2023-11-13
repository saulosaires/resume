package io.com.resume.user;

import io.com.resume.country.CountryService;
import io.com.resume.user.exception.InvalidUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFacade {

    private final CountryService countryService;
    private final UserService userService;
    private final UserMapper userMapper;

    public UserDto findById(Long id) {
        User user = userService.findById(id);
        return userMapper.toDto(user);
    }

    public UserDto findThinUser(Long id) {
        User user = userService.findThinUser(id);
        return userMapper.toDto(user);
    }

    public UserDto signIn(UserDto userDto) {

        log.info("UserFacade.signIn:{}", userDto);

        User user = userService.signInUpdate(userMapper.fromDto(userDto));
        return userMapper.toDto(user);
    }

    public UserDto update(UserDto userDto) throws InvalidUserException {

        if (Objects.nonNull(userDto.country().id()) && countryService.findById(userDto.country().id()).isEmpty()) {
            throw new InvalidUserException("Country does not exists", userDto.country().id());
        }

        User user = userService.update(userMapper.fromDto(userDto));
        return userMapper.toDto(user);

    }


}

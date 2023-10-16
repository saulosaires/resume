package io.com.resume.user;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserFacade(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public UserDto findById(Long id) {
        User user = userService.findById(id);
        return userMapper.toDto(user);
    }

    public UserDto findThinUser(Long id) {
        User user = userService.findThinUser(id);
        return userMapper.toDto(user);
    }
}

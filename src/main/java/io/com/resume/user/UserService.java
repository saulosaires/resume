package io.com.resume.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User findThinUser(Long id) {
        return repository.findThinUser(id).orElse(null);
    }
}

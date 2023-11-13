package io.com.resume.user;

import io.com.resume.user.exception.InvalidUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findThinUser(Long id) {
        return repository.findThinUser(id).orElse(null);
    }

    public User create(User user) throws InvalidUserException {

        var userExists = repository.existsByEmail(user.getEmail());

        if (!userExists) {
            log.warn("UserService.create user {} already exists", user.getEmail());
            throw new InvalidUserException("user %s already exists", user.getEmail());
        }

        return repository.save(user);
    }

    public User update(User user) throws InvalidUserException {

        User userToUpdate = repository.findById(user.getId())
                .orElseThrow(() -> new InvalidUserException("user %s not exists", user.getEmail()));

        userToUpdate.setName(user.getName());
        userToUpdate.setTelephone(user.getTelephone());
        userToUpdate.setWebsite(user.getWebsite());
        userToUpdate.setBirthDate(user.getBirthDate());
        userToUpdate.setInstantMessaging(user.getInstantMessaging());
        userToUpdate.setCountry(user.getCountry());
        userToUpdate.setSummary(user.getSummary());

        return repository.save(userToUpdate);
    }

    public User signInUpdate(User user) {
        AtomicReference<Long> id = new AtomicReference<>();
        repository.findByEmail(user.getEmail()).ifPresentOrElse(
                userSaved -> {
                    log.info("UserService signIn user found :{}", userSaved);
                    //userSaved.setName(user.getName());
                    userSaved.setPhotoUrl(user.getPhotoUrl());
                    id.set(repository.save(userSaved).getId());
                },
                () -> {
                    log.info("UserService signIn user not found :{}", user);
                    id.set(repository.save(user).getId());
                });

        return findThinUser(id.get());
    }
}

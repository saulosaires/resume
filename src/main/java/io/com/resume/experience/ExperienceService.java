package io.com.resume.experience;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository repository;

    public Experience findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    List<Experience> findByUserId(Long userId) {
        return repository.findByUser_Id(userId);
    }

    public Experience create(Experience experience) {
        return repository.save(experience);
    }
}

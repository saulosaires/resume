package io.com.resume.education;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository repository;


    public Education create(Education education) {
        return repository.save(education);
    }

    public List<Education> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}

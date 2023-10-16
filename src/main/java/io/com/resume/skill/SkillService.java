package io.com.resume.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository repository;

    public Skill findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Skill create(Skill skill) {
        return repository.save(skill);
    }

}

package io.com.resume.experience;

import io.com.resume.country.CountryService;
import io.com.resume.languages.language.LanguageService;
import io.com.resume.skill.Skill;
import io.com.resume.skill.SkillMapper;
import io.com.resume.skill.SkillService;
import io.com.resume.skill.dto.SkillDto;
import io.com.resume.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExperienceFacade {

    private final SkillService skillService;
    private final UserService userService;
    private final LanguageService languageService;
    private final CountryService countryService;
    private final ExperienceService service;
    private final ExperienceMapper mapper;
    private final SkillMapper skillMapper;


    public ExperienceDto findById(Long id) {
        return mapper.toDto(service.findById(id));
    }

    public List<ExperienceDto> findByUserId(Long userId) {
        log.info("ExperienceFacade.findByUserId: {}", userId);
        return service.findByUserId(userId).stream().map(mapper::toDto).toList();
    }

    public ExperienceDto create(ExperienceDto experienceDto) {

        Experience experience = mapper.fromDto(experienceDto);

        experience.setSkills(parseSkill(experienceDto.skills()));
        experience.setUser(userService.findById(experienceDto.user().id()));
        experience.setCountry(countryService.findById(experienceDto.country().id()).orElse(null));
        experience.setLanguage(languageService.findById(experienceDto.language().id()));

        Experience experienceCreated = service.create(experience);

        return mapper.toDto(experienceCreated);
    }

    private List<Skill> parseSkill(List<SkillDto> skillSDto) {

        List<Skill> skills = new ArrayList<>(skillSDto.size());

        skillSDto.forEach(skillDto -> {
            Skill skill = skillMapper.fromDto(skillDto);
            if (Objects.isNull(skill.getId())) {
                skills.add(skillService.create(skill));
            } else {
                skills.add(skill);
            }

        });

        return skills;
    }

}

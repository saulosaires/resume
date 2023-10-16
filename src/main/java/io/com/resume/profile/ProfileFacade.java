package io.com.resume.profile;

import io.com.resume.education.EducationDto;
import io.com.resume.education.EducationFacade;
import io.com.resume.experience.ExperienceDto;
import io.com.resume.experience.ExperienceFacade;
import io.com.resume.languages.user_language.UserLanguageDto;
import io.com.resume.languages.user_language.UserLanguageFacade;
import io.com.resume.link.LinkFacade;
import io.com.resume.link.dto.LinkDto;
import io.com.resume.user.UserDto;
import io.com.resume.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileFacade {

    private final UserFacade userFacade;
    private final LinkFacade linkFacade;
    private final EducationFacade educationFacade;
    private final ExperienceFacade experienceFacade;
    private final UserLanguageFacade userLanguageFacade;
    private final ProfileMapper profileMapper;


    public ProfileDto findById(Long id) {

        UserDto user = userFacade.findThinUser(id);
        List<LinkDto> links = linkFacade.findByUser(id);
        List<EducationDto> education = educationFacade.findByUserId(id);
        List<ExperienceDto> experiences = experienceFacade.findByUserId(id);
        List<UserLanguageDto> userLanguages = userLanguageFacade.findByUserId(id);

        return profileMapper.toDto(user, links, education, experiences, userLanguages);
    }

}

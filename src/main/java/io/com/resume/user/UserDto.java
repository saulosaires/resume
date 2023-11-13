package io.com.resume.user;

import io.com.resume.country.CountryDto;

import java.time.LocalDate;

public record UserDto(Long id,
                      String name,
                      String summary,
                      String email,
                      String telephone,
                      String website,
                      String instantMessaging,
                      LocalDate birthDate,
                      String photoUrl,
                      CountryDto country) {
}

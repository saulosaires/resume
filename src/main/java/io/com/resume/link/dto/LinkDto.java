package io.com.resume.link.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.com.resume.user.UserDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LinkDto(Long id, String label, String url, UserDto user) {

    public LinkDto(Long id, String label, String url) {
        this(id, label, url, null);
    }
}

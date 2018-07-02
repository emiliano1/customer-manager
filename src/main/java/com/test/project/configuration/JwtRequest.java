package com.test.project.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
public class JwtRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @Override
    public String toString() {
        return username;
    }
}

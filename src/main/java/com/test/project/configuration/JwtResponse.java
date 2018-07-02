package com.test.project.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class JwtResponse {

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token;
    }
}

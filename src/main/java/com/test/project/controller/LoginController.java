package com.test.project.controller;

import com.test.project.configuration.JwtRequest;
import com.test.project.configuration.JwtResponse;
import com.test.project.configuration.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController implements Serializable {

    private AuthenticationManager authenticationManager;
    private JwtToken provider;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtToken provider) {
        this.authenticationManager = authenticationManager;
        this.provider = provider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@Validated @RequestBody JwtRequest request, BindingResult errors) {
        if (Optional.ofNullable(request).isPresent()) {
            if (errors.hasErrors()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword());
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            String generatedToken = provider.generateToken(auth);

            return ResponseEntity.ok(new JwtResponse(generatedToken));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

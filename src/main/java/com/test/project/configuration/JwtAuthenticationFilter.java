package com.test.project.configuration;

import com.test.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtToken jwt;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = requestToken(request);
            if (Optional.ofNullable(token).isPresent() && jwt.validateToken(token)) {
                Long id = jwt.getUserId(token);
                UserDetails userDetails = userService.loadUserByUserId(id);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception ex) {
            logger.error("Error trying to validate user information.", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String requestToken(HttpServletRequest request) {
        String requestToken = request.getHeader("Authorization");
        if (Optional.ofNullable(requestToken).isPresent() && requestToken.startsWith("Bearer")) {
            return requestToken.substring(6, requestToken.length());
        }
        return null;
    }
}

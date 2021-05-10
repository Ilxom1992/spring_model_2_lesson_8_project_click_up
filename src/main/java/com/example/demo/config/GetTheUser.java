package com.example.demo.config;

import com.example.demo.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class GetTheUser implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null
                && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")
        ) {
            User user=(User)authentication.getPrincipal();
            return null;
        }

        return Optional.empty();
    }

    public Optional<User> getCurrentAuditorUser() {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null
                && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")
        ) {
            User user=(User)authentication.getPrincipal();
            return Optional.of(user);
        }

        return Optional.empty();
    }
}

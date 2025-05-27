package com.neosoft.auditing.config;

import com.neosoft.security.model.CustomerUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated())
        {
            return Optional.of("SYSTEM");   //fallback
        }
        Object principal = auth.getPrincipal();
        if (principal instanceof CustomerUserDetails userDetails)
        {
            return Optional.of(userDetails.getUsername());
        }
        else {
            return Optional.of(principal.toString());
        }
    }
}

package com.neosoft.auditing.config;

import com.neosoft.security.model.CustomerUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider(){
      //  return new AuditorAwareImpl();
        return () -> {
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
        };
    }
}

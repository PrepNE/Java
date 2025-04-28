package com.mikepn.bankingsystem.v1.config;



import com.mikepn.bankingsystem.v1.models.User;
import com.mikepn.bankingsystem.v1.security.User.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class ApplicationAuditWare implements AuditorAware<UUID> {
    @Override
    public Optional getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }
        UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal();
        return Optional.ofNullable(userPrinciple.getId());
    }
}
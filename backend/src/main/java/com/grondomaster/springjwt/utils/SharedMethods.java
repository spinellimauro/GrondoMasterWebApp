package com.grondomaster.springjwt.utils;

import com.grondomaster.springjwt.security.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SharedMethods {

    protected static UserDetailsImpl getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (UserDetailsImpl) authentication.getPrincipal();
    }
}

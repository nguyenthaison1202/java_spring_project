package com.mockproject.group3.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetAuthUserInfo {

    public int getAuthUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String username = authentication.getName();

        var userDetails = authentication.getPrincipal();
        // int userId = (int) userDetails.getId();

        return 0;
    }
}

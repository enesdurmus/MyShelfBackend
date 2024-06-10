package com.enesd.myshelfbackend.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class AuthPathHelper {
    private static final RequestMatcher authMatcher = new AntPathRequestMatcher("/api/*/auth/**");

    public static boolean isAuthPath(HttpServletRequest request) {
        return authMatcher.matcher(request).isMatch();
    }
}
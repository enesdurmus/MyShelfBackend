package com.enesd.myshelfbackend.filters;

import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.services.RateLimitService;
import com.enesd.myshelfbackend.utils.AuthPathHelper;
import com.enesd.myshelfbackend.utils.SwaggerPathHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {
    private final RateLimitService rateLimitService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        rateLimitService.checkRateLimit(user);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return SwaggerPathHelper.isSwaggerPath(request) || AuthPathHelper.isAuthPath(request);
    }
}

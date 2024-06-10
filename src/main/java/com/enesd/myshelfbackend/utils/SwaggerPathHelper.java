package com.enesd.myshelfbackend.utils;

import jakarta.servlet.http.HttpServletRequest;

public class SwaggerPathHelper {
    public static boolean isSwaggerPath(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs");
    }
}
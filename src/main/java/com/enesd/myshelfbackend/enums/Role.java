package com.enesd.myshelfbackend.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN(Code.ADMIN),
    USER(Code.USER);

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public static Role fromString(String authority) {
        for (Role role : Role.values()) {
            if (role.getAuthority().equalsIgnoreCase(authority)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant with authority " + authority);
    }

    public static class Code {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";
    }
}
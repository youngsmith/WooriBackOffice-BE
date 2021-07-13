package com.woori.wooribackoffice.security.constant;

import lombok.Getter;

@Getter
public enum RoleType {
    USER("USER"),
    ADMIN("ADMIN");

    private final String name;

    RoleType(String name) {
        this.name = name;
    }
}

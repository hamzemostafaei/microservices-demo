package com.microservices.demo.elastic.query.service.security;

import lombok.Getter;

@Getter
public enum PermissionTypeEnum {

    READ("READ"), WRITE("WRITE"), ADMIN("ADMIN");

    private final String type;

    PermissionTypeEnum(String type) {
        this.type = type;
    }

}

package com.microservices.demo.elastic.query.service.data.access.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_permissions")
public class UserPermissionEntity {

    @NotNull
    @Id
    @Column(name = "id", length = 64, nullable = false)
    private String id;

    @NotNull
    @Column(name = "username", length = 32, nullable = false)
    private String username;

    @NotNull
    @Column(name = "documentId", length = 64, nullable = false)
    private String documentId;

    @NotNull
    @Column(name = "permissionType", length = 32, nullable = false)
    private String permissionType;

}

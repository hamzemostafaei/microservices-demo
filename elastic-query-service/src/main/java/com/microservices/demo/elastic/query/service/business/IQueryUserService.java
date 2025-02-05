package com.microservices.demo.elastic.query.service.business;

import com.microservices.demo.elastic.query.service.data.access.entity.UserPermissionEntity;

import java.util.List;
import java.util.Optional;

public interface IQueryUserService {
    Optional<List<UserPermissionEntity>> findAllPermissionsByUsername(String username);
}

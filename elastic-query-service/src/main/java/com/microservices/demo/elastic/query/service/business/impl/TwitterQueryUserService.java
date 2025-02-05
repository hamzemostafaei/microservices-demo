package com.microservices.demo.elastic.query.service.business.impl;

import com.microservices.demo.elastic.query.service.business.IQueryUserService;
import com.microservices.demo.elastic.query.service.data.access.entity.UserPermissionEntity;
import com.microservices.demo.elastic.query.service.data.access.repository.api.IUserPermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterQueryUserService implements IQueryUserService {
    private final IUserPermissionRepository userPermissionRepository;

    @Override
    public Optional<List<UserPermissionEntity>> findAllPermissionsByUsername(String username) {
        if (log.isInfoEnabled()) {
            log.info("Finding permissions by username {}", username);
        }
        return userPermissionRepository.findPermissionsByUsername(username);
    }
}

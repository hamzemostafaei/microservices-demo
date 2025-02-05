package com.microservices.demo.elastic.query.service.transformer;

import com.microservices.demo.elastic.query.service.data.access.entity.UserPermissionEntity;
import com.microservices.demo.elastic.query.service.security.PermissionTypeEnum;
import com.microservices.demo.elastic.query.service.security.TwitterQueryUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserPermissionsToUserDetailTransformer {

    public TwitterQueryUser getUserDetails(List<UserPermissionEntity> userPermissions) {
        return TwitterQueryUser.builder()
                .username(userPermissions.getFirst().getUsername())
                .permissions(userPermissions.stream()
                        .collect(Collectors.toMap(
                                UserPermissionEntity::getDocumentId,
                                permission -> PermissionTypeEnum.valueOf(permission.getPermissionType()))))
                .build();
    }
}

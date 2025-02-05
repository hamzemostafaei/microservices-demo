package com.microservices.demo.elastic.query.service.data.access.repository.api;

import com.microservices.demo.elastic.query.service.data.access.entity.UserPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserPermissionRepository extends JpaRepository<UserPermissionEntity, String> {

    @Query(nativeQuery = true, value = """
            select p.user_permission_id as id, u.username, d.document_id, p.permission_type
                from users u, user_permissions p, documents d
                where u.id = p.user_id
                and d.id = p.document_id
                and u.username = :username
            """)
    Optional<List<UserPermissionEntity>> findPermissionsByUsername(@Param("username") String username);

}
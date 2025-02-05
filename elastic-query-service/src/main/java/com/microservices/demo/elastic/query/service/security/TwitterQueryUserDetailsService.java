package com.microservices.demo.elastic.query.service.security;

import com.microservices.demo.elastic.query.service.business.IQueryUserService;
import com.microservices.demo.elastic.query.service.transformer.UserPermissionsToUserDetailTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterQueryUserDetailsService implements UserDetailsService {

    private final IQueryUserService queryUserService;

    private final UserPermissionsToUserDetailTransformer userPermissionsToUserDetailTransformer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return queryUserService
                .findAllPermissionsByUsername(username)
                .map(userPermissionsToUserDetailTransformer::getUserDetails)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("No user found with name %s", username)));

    }
}

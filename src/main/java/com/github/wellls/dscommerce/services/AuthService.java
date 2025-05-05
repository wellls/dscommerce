package com.github.wellls.dscommerce.services;

import com.github.wellls.dscommerce.entities.User;
import com.github.wellls.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(long userId) {
        User me = userService.getAuthenticatedUser();
        if(!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
            throw new ForbiddenException();
        }
    }
}

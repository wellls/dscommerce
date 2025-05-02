package com.github.wellls.dscommerce.services;

import com.github.wellls.dscommerce.dtos.UserDTO;
import com.github.wellls.dscommerce.entities.Role;
import com.github.wellls.dscommerce.entities.User;
import com.github.wellls.dscommerce.projections.UserDetailsProjection;
import com.github.wellls.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if(result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = new User();
        user.setEmail(result.getFirst().getUsername());
        user.setPassword(result.getFirst().getPassword());
        for(UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }

    @Transactional(readOnly = true)
    public UserDTO getAuthenticatedUserData() {
        User user = getAuthenticatedUser();
        return new UserDTO(user);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User not authenticated");
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof Jwt jwtPrincipal)) {
            throw new AuthenticationCredentialsNotFoundException("Invalid authentication type");
        }
        String username = jwtPrincipal.getClaim("username");
        if (username == null || username.trim().isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}

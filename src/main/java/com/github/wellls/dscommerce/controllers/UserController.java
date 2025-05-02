package com.github.wellls.dscommerce.controllers;

import com.github.wellls.dscommerce.dtos.ProductDTO;
import com.github.wellls.dscommerce.dtos.UserDTO;
import com.github.wellls.dscommerce.services.ProductService;
import com.github.wellls.dscommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getAuthenticatedUserData() {
        return ResponseEntity.ok(userService.getAuthenticatedUserData());
    }
}

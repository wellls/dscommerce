package com.github.wellls.dscommerce.controllers;

import com.github.wellls.dscommerce.dto.ProductDTO;
import com.github.wellls.dscommerce.entities.Product;
import com.github.wellls.dscommerce.repositories.ProductRepository;
import com.github.wellls.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping()
    public Page<ProductDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }
}
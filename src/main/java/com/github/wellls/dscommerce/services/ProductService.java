package com.github.wellls.dscommerce.services;

import com.github.wellls.dscommerce.dto.ProductDTO;
import com.github.wellls.dscommerce.entities.Product;
import com.github.wellls.dscommerce.repositories.ProductRepository;
import com.github.wellls.dscommerce.services.exceptions.DatabaseException;
import com.github.wellls.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException());
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable) {
        Page<Product> products = repository.searchByName(name, pageable);
        return products.map(product -> new ProductDTO(product));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        this.copyDtoToEntity(dto, product);
        product = repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product product = repository.getReferenceById(id);
            this.copyDtoToEntity(dto, product);
            product = repository.save(product);
            return new ProductDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException();
        }

        try {
            repository.deleteById(id);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity violation while deleting resource with id " + id);
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
    }
}

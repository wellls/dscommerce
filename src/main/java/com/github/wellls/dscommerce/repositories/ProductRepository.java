package com.github.wellls.dscommerce.repositories;

import com.github.wellls.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

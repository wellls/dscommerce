package com.github.wellls.dscommerce.repositories;

import com.github.wellls.dscommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

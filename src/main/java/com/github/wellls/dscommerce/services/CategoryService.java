package com.github.wellls.dscommerce.services;

import com.github.wellls.dscommerce.dtos.CategoryDTO;
import com.github.wellls.dscommerce.entities.Category;
import com.github.wellls.dscommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryDTO(category)).toList();
    }
}

package com.github.wellls.dscommerce.dtos;

import com.github.wellls.dscommerce.entities.Category;
import com.github.wellls.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    private Long id;

    @Size(min = 3, max = 80, message = "Name must be between 3 and 80 characters long")
    @NotBlank(message = "Required field")
    private String name;

    @Size(min = 10, message = "Description must be at least 10 characters long")
    @NotBlank(message = "Required field")
    private String description;

    @Positive(message = "Price must be positive")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "Categories should not be empty")
    private final List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
        for(Category category : product.getCategories()) {
            categories.add(new CategoryDTO(category));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}

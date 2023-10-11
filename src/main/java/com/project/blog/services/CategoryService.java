package com.project.blog.services;

import com.project.blog.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategory(Long id);
}

package com.project.blog.servicesImpl;

import com.project.blog.exceptions.ResourceNotFound;
import com.project.blog.models.Category;
import com.project.blog.repositories.CategoryRepository;
import com.project.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Category", "Id", id)
        );
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Category oldCategory = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Category", "Id", id)
        );
        oldCategory.setTitle(category.getTitle());
        oldCategory.setDescription(category.getDescription());
        return categoryRepository.save(oldCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Category", "Id", id)
        );
        categoryRepository.delete(category);
    }
}

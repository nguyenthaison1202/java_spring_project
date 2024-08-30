package com.mockproject.group3.service;

import com.mockproject.group3.dto.CategoryDTO;
import com.mockproject.group3.model.Category;
import com.mockproject.group3.model.Course;
import com.mockproject.group3.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public Set<Course> getCourseByCategoryId(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(Category::getCourses).orElse(null);
    }

    public Category saveCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setCreated_at(LocalDateTime.now());
        category.setUpdated_at(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    public Category updateCategory(int id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            category.setCreated_at(categoryDTO.getCreatedAt());
            category.setUpdated_at(LocalDateTime.now());
            return categoryRepository.save(category);
        }
        return null;
    }

    @Transactional
    public boolean deleteCategory(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            try {
                categoryRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                // Log the exception
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}

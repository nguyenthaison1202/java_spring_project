package com.mockproject.group3.controller;

import com.mockproject.group3.dto.CategoryDTO;
import com.mockproject.group3.model.Category;
import com.mockproject.group3.model.Course;
import com.mockproject.group3.service.CategoryService;
import com.mockproject.group3.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/courses")
        public ResponseEntity<Set<Course>> getCoursesByCategoryId(@PathVariable int id) {
            Set<Course> courses = categoryService.getCourseByCategoryId(id);
            return courses != null ? ResponseEntity.ok(courses) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Category createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = categoryService.updateCategory(id, categoryDTO);
        return updatedCategory != null ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.status(500).build();
    }
}

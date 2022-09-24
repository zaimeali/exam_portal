package com.exam.portal.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.entity.exam.Category;
import com.exam.portal.service.implementation.CategoryServiceImpl;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {
    
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(
            categoryServiceImpl.addCategory(category)
        );
    }

    @GetMapping("/")
    public ResponseEntity<Set<Category>> getAllCategories() {
        return ResponseEntity.ok(
            categoryServiceImpl.getAllCategories()
        );
    }

    @GetMapping("/{categoryID}")
    public Category getCategory(@PathVariable("categoryID") Long categoryID) {
        return categoryServiceImpl.getCategoryByID(categoryID);
    }

    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category) {
        return categoryServiceImpl.updateCategory(category);
    }

    @DeleteMapping("/{categoryID}")
    public void deleteCategoryByID(@PathVariable("categoryID") Long categoryID) {
        categoryServiceImpl.deleteCategoryByID(categoryID);
    }
}

package com.exam.portal.service.implementation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.entity.exam.Category;
import com.exam.portal.repository.CategoryRepository;
import com.exam.portal.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Set<Category> getAllCategories() {
        return new HashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category getCategoryByID(Long categoryID) {
        return categoryRepository.findById(categoryID).get();
    }

    @Override
    public void deleteCategoryByID(Long categoryID) {
        categoryRepository.deleteById(categoryID);
    }
    
}

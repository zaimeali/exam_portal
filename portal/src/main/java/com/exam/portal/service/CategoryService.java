package com.exam.portal.service;

import java.util.Set;

import com.exam.portal.entity.exam.Category;

public interface CategoryService {
    
    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public Set<Category> getAllCategories();

    public Category getCategoryByID(Long categoryID);

    public void deleteCategoryByID(Long categoryID);
}

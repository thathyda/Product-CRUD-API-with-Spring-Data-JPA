package com.practiceistad.mvc_taskwork.service;

import com.practiceistad.mvc_taskwork.dto.CategoryRequest;
import com.practiceistad.mvc_taskwork.dto.CategoryResponse;
import com.practiceistad.mvc_taskwork.model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAllCat();

    CategoryResponse findCategoryById(Integer id);

    CategoryResponse findCategoryByName(String name);

    void createCategory(CategoryRequest categoryRequest);

    CategoryResponse editCategoryById(Integer id, CategoryRequest request);

    void deleteCategoryById(Integer id);
}

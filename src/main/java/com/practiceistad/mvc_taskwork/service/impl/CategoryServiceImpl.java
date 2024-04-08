package com.practiceistad.mvc_taskwork.service.impl;

import com.practiceistad.mvc_taskwork.dto.CategoryRequest;
import com.practiceistad.mvc_taskwork.dto.CategoryResponse;
import com.practiceistad.mvc_taskwork.model.Category;
import com.practiceistad.mvc_taskwork.repository.CategoryRepository;
import com.practiceistad.mvc_taskwork.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> findAllCat() {

        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(c -> new CategoryResponse(
                        c.getName(), c.getDescription()))
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Not Found"
                ));
        return new CategoryResponse(category.getName(),category.getDescription());
    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        return null;
    }

    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByName(categoryRequest.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already existed in our system!!"
            );
        }

        Category category = new Category();
        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse editCategoryById(Integer id, CategoryRequest request) {
        // Load old data
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category don't found!"
                ));
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
        //Kjey logic
        return this.findCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        if (!categoryRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category don't found!!"
            );
        }
        categoryRepository.deleteById(id);
    }

}

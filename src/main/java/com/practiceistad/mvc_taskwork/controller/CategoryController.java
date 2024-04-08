package com.practiceistad.mvc_taskwork.controller;

import com.practiceistad.mvc_taskwork.dto.CategoryRequest;
import com.practiceistad.mvc_taskwork.dto.CategoryResponse;
import com.practiceistad.mvc_taskwork.model.Product;
import com.practiceistad.mvc_taskwork.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    CategoryResponse findCategoryById(@PathVariable Integer id) {
        return categoryService.findCategoryById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewCategory(@Valid @RequestBody CategoryRequest request) {
        categoryService.createCategory(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{id}")
    CategoryResponse editCategoryById(@PathVariable Integer id, @Valid @RequestBody CategoryRequest request) {
        return categoryService.editCategoryById(id, request);
    }

    //Customize Swagger
    @Operation(summary = "Get All Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Categories not found",
                    content = @Content)
    })

    @GetMapping
    List<CategoryResponse> findCategories() {

        return categoryService.findAllCat();
    }
}

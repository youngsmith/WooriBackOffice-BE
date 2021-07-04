package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.dto.request.CategoryRequest;
import com.woori.wooribackoffice.domain.dto.response.CategoryResponse;
import com.woori.wooribackoffice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping
    public ResponseEntity<String> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(categoryRequest);
    }
}

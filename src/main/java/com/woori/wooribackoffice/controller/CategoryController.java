package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.dto.request.CategoryRequest;
import com.woori.wooribackoffice.domain.dto.response.CategoryResponse;
import com.woori.wooribackoffice.exception.ForeignKeyConstraintViolationException;
import com.woori.wooribackoffice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.updateCategory(categoryRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }
}

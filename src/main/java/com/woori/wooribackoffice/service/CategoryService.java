package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.domain.dto.request.CategoryRequest;
import com.woori.wooribackoffice.domain.dto.response.CategoryResponse;
import com.woori.wooribackoffice.domain.entity.Category;
import com.woori.wooribackoffice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ResponseEntity<CategoryResponse> getCategoryById(final Long id) {
        return ResponseEntity.ok().body(CategoryResponse.from(categoryRepository.getById(id)));
    }

    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok().body(CategoryResponse.from(categoryRepository.findAll()));
    }

    public ResponseEntity<String> createCategory(final CategoryRequest categoryRequest) {
        categoryRepository.save(Category.from(categoryRequest));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> updateCategory(final CategoryRequest categoryRequest) {
        Category category = categoryRepository.getById(categoryRequest.getId());
        category.update(categoryRequest);
        categoryRepository.save(category);  // save 를 안해주면 저장이 되지 않음
        return ResponseEntity.ok().build();
    }
}

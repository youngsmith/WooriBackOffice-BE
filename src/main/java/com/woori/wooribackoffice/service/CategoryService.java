package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.domain.dto.request.CategoryRequest;
import com.woori.wooribackoffice.domain.dto.request.FarmRequest;
import com.woori.wooribackoffice.domain.dto.response.CategoryResponse;
import com.woori.wooribackoffice.domain.dto.response.FarmResponse;
import com.woori.wooribackoffice.domain.entity.CategoryEntity;
import com.woori.wooribackoffice.domain.entity.FarmEntity;
import com.woori.wooribackoffice.repository.CategoryRepository;
import com.woori.wooribackoffice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse getCategoryById(long id) {
        categoryRepository.getById(id);
        return null;
    }

    public List<CategoryResponse> getAllCategories() {
        categoryRepository.findAll();
        return Collections.emptyList();
    }

    public ResponseEntity<String> createCategory(CategoryRequest categoryRequest) {
        categoryRepository.save(CategoryEntity.from(categoryRequest));
        return null;
    }

    public ResponseEntity<String> updateCategory(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = categoryRepository.getById(1L);
        categoryEntity.update(categoryRequest);
        return null;
    }
}

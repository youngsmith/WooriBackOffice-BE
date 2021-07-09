package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.domain.dto.request.CategoryRequest;
import com.woori.wooribackoffice.domain.dto.response.CategoryResponse;
import com.woori.wooribackoffice.domain.entity.Category;
import com.woori.wooribackoffice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse getCategoryById(final Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));

        return CategoryResponse.from(category);
    }

    public List<CategoryResponse> getAllCategories() {
        return CategoryResponse.from(categoryRepository.findAll());
    }

    public void createCategory(final CategoryRequest categoryRequest) {
        categoryRepository.save(Category.from(categoryRequest));
    }

    public void updateCategory(final CategoryRequest categoryRequest) {
        Category category = categoryRepository.getById(categoryRequest.getId());
        category.update(categoryRequest);
        categoryRepository.save(category);  // save 를 안해주면 저장이 되지 않음
    }
}

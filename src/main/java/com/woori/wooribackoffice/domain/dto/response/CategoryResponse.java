package com.woori.wooribackoffice.domain.dto.response;

import com.woori.wooribackoffice.domain.entity.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CategoryResponse {
    private String name;
    private String description;

    public static CategoryResponse from(final Category category) {
        return new CategoryResponse()
                .setDescription(category.getDescription())
                .setName(category.getName());
    }

    public static List<CategoryResponse> from(final List<Category> categoryEntities) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for(Category category : categoryEntities) {
            categoryResponses.add(from(category));
        }
        return categoryResponses;
    }
}

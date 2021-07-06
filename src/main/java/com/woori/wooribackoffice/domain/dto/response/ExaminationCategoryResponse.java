package com.woori.wooribackoffice.domain.dto.response;

import com.woori.wooribackoffice.domain.entity.ExaminationCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ExaminationCategoryResponse {
    private Long id;
    private Long categoryId;
    private String result;

    public static List<ExaminationCategoryResponse> from(List<ExaminationCategory> examinationCategories) {
        List<ExaminationCategoryResponse> examinationCategoryResponses = new ArrayList<>();

        for(ExaminationCategory examinationCategory : examinationCategories) {
            ExaminationCategoryResponse examinationCategoryResponse = new ExaminationCategoryResponse().setCategoryId(examinationCategory.getCategory().getId())
                    .setResult(examinationCategory.getResult())
                    .setId(examinationCategory.getId());

            examinationCategoryResponses.add(examinationCategoryResponse);
        }
        return examinationCategoryResponses;
    }
}

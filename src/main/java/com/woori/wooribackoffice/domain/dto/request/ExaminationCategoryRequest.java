package com.woori.wooribackoffice.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExaminationCategoryRequest {
    private Long id;
    private Long categoryId;
    private String result;
}

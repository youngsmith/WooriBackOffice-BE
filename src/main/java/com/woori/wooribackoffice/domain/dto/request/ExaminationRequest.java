package com.woori.wooribackoffice.domain.dto.request;

import com.woori.wooribackoffice.util.CustomStringUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ExaminationRequest {
    private Long id;
    private LocalDate examinationDate;
    private String registrationNumber;
    private FarmRequest farm;
    private List<ExaminationCategoryRequest> examinationCategories;

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = CustomStringUtil.removeAllWhiteSpace(registrationNumber);
    }
}

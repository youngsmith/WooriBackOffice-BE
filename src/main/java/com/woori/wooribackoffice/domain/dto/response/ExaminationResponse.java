package com.woori.wooribackoffice.domain.dto.response;

import com.woori.wooribackoffice.domain.entity.Examination;
import com.woori.wooribackoffice.domain.entity.ExaminationCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ExaminationResponse {
    private Long id;
    private LocalDate examinationDate;
    private String registrationNumber;
    private FarmResponse farmResponse;
    private List<ExaminationCategoryResponse> examinationCategoryResponses;

    public static ExaminationResponse of(Examination examination, List<ExaminationCategory> examinationCategories) {
        return new ExaminationResponse().setId(examination.getId())
                .setExaminationDate(examination.getExaminationDate())
                .setFarmResponse(FarmResponse.from(examination.getFarm()))
                .setExaminationCategoryResponses(ExaminationCategoryResponse.from(examinationCategories))
                .setRegistrationNumber(examination.getRegistrationNumber());
    }
}

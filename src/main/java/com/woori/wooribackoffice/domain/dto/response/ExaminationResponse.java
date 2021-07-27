package com.woori.wooribackoffice.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate examinationDate;
    private String registrationNumber;
    private FarmResponse farm;
    private List<ExaminationCategoryResponse> examinationCategories;

    public static ExaminationResponse of(final Examination examination, final List<ExaminationCategory> examinationCategories) {
        return new ExaminationResponse().setId(examination.getId())
                .setExaminationDate(examination.getExaminationDate())
                .setFarm(FarmResponse.from(examination.getFarm()))
                .setExaminationCategories(ExaminationCategoryResponse.from(examinationCategories))
                .setRegistrationNumber(examination.getRegistrationNumber());
    }
}

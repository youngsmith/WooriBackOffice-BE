package com.woori.wooribackoffice.domain.dto.request;

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
    private FarmRequest farmRequest;
    private List<ExaminationCategoryRequest> examinationCategoryRequests;
}

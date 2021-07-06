package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.domain.dto.request.ExaminationCategoryRequest;
import com.woori.wooribackoffice.domain.dto.request.ExaminationRequest;
import com.woori.wooribackoffice.domain.dto.response.ExaminationResponse;
import com.woori.wooribackoffice.domain.entity.Examination;
import com.woori.wooribackoffice.domain.entity.ExaminationCategory;
import com.woori.wooribackoffice.domain.entity.Farm;
import com.woori.wooribackoffice.repository.ExaminationCategoryRepository;
import com.woori.wooribackoffice.repository.ExaminationRepository;
import com.woori.wooribackoffice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExaminationService {
    private final ExaminationRepository examinationRepository;
    private final ExaminationCategoryRepository examinationCategoryRepository;
    private final FarmRepository farmRepository;

    public ExaminationResponse getExaminationById(final Long id) {
        Examination examination = examinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("검사 정보를 찾기 못했습니다."));

        List<ExaminationCategory> examinationCategories = examinationCategoryRepository.findByExaminationId(id);
        return ExaminationResponse.of(examination, examinationCategories);
    }

    @Transactional
    public ResponseEntity<String> createExamination(final ExaminationRequest examinationRequest) {
        // getById 함수와 리턴값이 다름
        Farm farm = farmRepository.findById(examinationRequest.getFarmRequest().getId())
                .orElseThrow(() -> new EntityNotFoundException("농장 정보를 찾기 못했습니다."));

        Examination examination = examinationRepository.save(Examination.of(examinationRequest, farm));

        examinationCategoryRepository.deleteByExaminationId(examinationRequest.getId());

        List<ExaminationCategoryRequest> examinationCategoryRequests = examinationRequest.getExaminationCategoryRequests();

        ExaminationCategory.from(examinationCategoryRequests, examination);

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<String> updateExamination(final ExaminationRequest examinationRequest) {
        Examination examination = examinationRepository.getById(1L);
        examination.update(examinationRequest);
        return null;
    }
}

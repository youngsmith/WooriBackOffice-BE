package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.domain.dto.request.ExaminationRequest;
import com.woori.wooribackoffice.domain.dto.response.ExaminationResponse;
import com.woori.wooribackoffice.domain.entity.Category;
import com.woori.wooribackoffice.domain.entity.Examination;
import com.woori.wooribackoffice.domain.entity.ExaminationCategory;
import com.woori.wooribackoffice.domain.entity.Farm;
import com.woori.wooribackoffice.repository.CategoryRepository;
import com.woori.wooribackoffice.repository.ExaminationCategoryRepository;
import com.woori.wooribackoffice.repository.ExaminationRepository;
import com.woori.wooribackoffice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExaminationService {
    private final ExaminationRepository examinationRepository;
    private final ExaminationCategoryRepository examinationCategoryRepository;
    private final FarmRepository farmRepository;
    private final CategoryRepository categoryRepository;

    public ExaminationResponse getExaminationById(final Long id) {
        Examination examination = examinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("검사 정보를 찾지 못했습니다."));

        List<ExaminationCategory> examinationCategories = examinationCategoryRepository.findByExaminationId(id);
        return ExaminationResponse.of(examination, examinationCategories);
    }

    // https://interconnection.tistory.com/123 : @transactional 어떤 어노테이션을 사용해야 할까?
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void createExamination(final ExaminationRequest examinationRequest) {
        // getById 함수와 리턴값이 다름
        Farm farm = farmRepository.findById(examinationRequest.getFarmRequest().getId())
                .orElseThrow(() -> new EntityNotFoundException("농장 정보를 찾기 못했습니다."));

        Examination examination = examinationRepository.save(Examination.of(examinationRequest, farm));

        // Map 으로 만들 때 중복 키 값이 있으면, exception 을 던짐 -> 의도적인 처리
        Map<Long, Category> categoryMapById = examinationRequest.getExaminationCategoryRequests()
                .stream()
                .map(e -> categoryRepository.findById(e.getCategoryId())
                        .orElseThrow(() -> new EntityNotFoundException("카테고리 정보를 찾기 못했습니다.")))
                .collect(Collectors.toMap(Category::getId, Function.identity()));

        examinationCategoryRepository.saveAll(ExaminationCategory.of(examination, categoryMapById, examinationRequest.getExaminationCategoryRequests()));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateExamination(final ExaminationRequest examinationRequest) {
        Examination examination = examinationRepository.findById(examinationRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("검사 정보를 찾기 못했습니다."));

        Farm farm = farmRepository.findById(examinationRequest.getFarmRequest().getId())
                .orElseThrow(() -> new EntityNotFoundException("농장 정보를 찾기 못했습니다."));

        examination.update(examinationRequest, farm);

        examinationCategoryRepository.deleteByExaminationId(examinationRequest.getId());

        Map<Long, Category> categoryMapById = examinationRequest.getExaminationCategoryRequests()
                .stream()
                .map(e -> categoryRepository.findById(e.getCategoryId())
                        .orElseThrow(() -> new EntityNotFoundException("카테고리 정보를 찾기 못했습니다.")))
                .collect(Collectors.toMap(Category::getId, Function.identity()));

        examinationCategoryRepository.saveAll(ExaminationCategory.of(examination, categoryMapById, examinationRequest.getExaminationCategoryRequests()));
    }
}

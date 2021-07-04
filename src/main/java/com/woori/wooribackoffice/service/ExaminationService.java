package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.domain.dto.request.ExaminationRequest;
import com.woori.wooribackoffice.domain.dto.response.ExaminationResponse;
import com.woori.wooribackoffice.domain.entity.Examination;
import com.woori.wooribackoffice.repository.ExaminationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationService {
    private final ExaminationRepository examinationRepository;

    public List<ExaminationResponse> getAllExaminations() {
        examinationRepository.findAll();
        return Collections.emptyList();
    }

    public ExaminationResponse getExaminationById(final Long id) {
        examinationRepository.getById(id);
        return null;
    }

    public ResponseEntity<String> createExamination(final ExaminationRequest examinationRequest) {
        examinationRepository.save(Examination.from(examinationRequest));
        return null;
    }

    public ResponseEntity<String> updateExamination(final ExaminationRequest examinationRequest) {
        Examination examination = examinationRepository.getById(1L);
        examination.update(examinationRequest);
        return null;
    }
}

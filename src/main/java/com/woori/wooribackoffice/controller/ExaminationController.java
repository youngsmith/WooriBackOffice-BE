package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.dto.request.ExaminationRequest;
import com.woori.wooribackoffice.domain.dto.response.ExaminationResponse;
import com.woori.wooribackoffice.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/examination")
public class ExaminationController {
    private final ExaminationService examinationService;

    @GetMapping("/{id}")
    public ExaminationResponse getExaminationById(@PathVariable Long id) {
        return examinationService.getExaminationById(id);
    }

    @PostMapping
    public ResponseEntity<String> createExamination(@RequestBody ExaminationRequest examinationRequest) {
        examinationService.createExamination(examinationRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> updateExamination(@RequestBody ExaminationRequest examinationRequest) {
        examinationService.updateExamination(examinationRequest);
        return ResponseEntity.ok().build();
    }
}

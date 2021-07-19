package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.dto.request.ExaminationRequest;
import com.woori.wooribackoffice.domain.dto.request.SearchParam;
import com.woori.wooribackoffice.domain.dto.response.ExaminationResponse;
import com.woori.wooribackoffice.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/examination")
public class ExaminationController {
    private final ExaminationService examinationService;

    @GetMapping("/{id}")
    public ResponseEntity<ExaminationResponse> getExaminationById(@PathVariable Long id) {
        return ResponseEntity.ok(examinationService.getExaminationById(id));
    }

    @GetMapping
    public ResponseEntity<List<ExaminationResponse>> search(SearchParam searchParam) {
        return ResponseEntity.ok(examinationService.searchExaminations(searchParam));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFarm(@PathVariable Long id) {
        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        examinationService.deleteExaminationById(id);
        return ResponseEntity.ok().build();
    }
}

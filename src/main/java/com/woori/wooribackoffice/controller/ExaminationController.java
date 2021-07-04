package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.dto.request.FarmRequest;
import com.woori.wooribackoffice.domain.dto.response.FarmResponse;
import com.woori.wooribackoffice.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/examination")
public class ExaminationController {
    private final FarmService farmService;

    @GetMapping("/")
    public List<FarmResponse> getAllFarms() {
        return farmService.getAllFarms();
    }

    @GetMapping("/{id}")
    public FarmResponse getFarmById(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }

    @PostMapping
    public ResponseEntity<String> createFarm(@RequestBody FarmRequest farmRequest) {
        return farmService.createFarm(farmRequest);
    }

    @PutMapping
    public ResponseEntity<String> updateFarm(@RequestBody FarmRequest farmRequest) {
        return farmService.updateFarm(farmRequest);
    }
}

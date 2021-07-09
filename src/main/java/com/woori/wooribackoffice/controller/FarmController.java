package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.dto.request.FarmRequest;
import com.woori.wooribackoffice.domain.dto.response.FarmResponse;
import com.woori.wooribackoffice.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/farm")
public class FarmController {
    private final FarmService farmService;

    @GetMapping("/{id}")
    public ResponseEntity<FarmResponse> getFarmById(@PathVariable Long id) {
        return ResponseEntity.ok(farmService.getFarmById(id));
    }

    @PostMapping
    public ResponseEntity<String> createFarm(@RequestBody FarmRequest farmRequest) {
        farmService.createFarm(farmRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> updateFarm(@RequestBody FarmRequest farmRequest) {
        farmService.updateFarm(farmRequest);
        return ResponseEntity.ok().build();
    }
}

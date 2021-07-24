package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.dto.request.FarmRequest;
import com.woori.wooribackoffice.domain.dto.request.SearchParam;
import com.woori.wooribackoffice.domain.dto.response.FarmResponse;
import com.woori.wooribackoffice.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farm")
public class FarmController {
    private final FarmService farmService;

    @GetMapping("/search")
    public ResponseEntity<List<FarmResponse>> searchFarm(@RequestParam String farmName) {
        return ResponseEntity.ok(farmService.searchFarm(farmName));
    }

    @GetMapping("/")
    public ResponseEntity<List<FarmResponse>> getAllFarms() {
        return ResponseEntity.ok(farmService.getAllFarms());
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFarm(@PathVariable Long id) {
        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        farmService.deleteFarmById(id);
        return ResponseEntity.ok().build();
    }
}

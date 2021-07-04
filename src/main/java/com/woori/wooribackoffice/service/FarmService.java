package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.domain.dto.request.FarmRequest;
import com.woori.wooribackoffice.domain.dto.response.FarmResponse;
import com.woori.wooribackoffice.domain.entity.FarmEntity;
import com.woori.wooribackoffice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;

    public FarmResponse getFarmById(long id) {
        return null;
    }

    public List<FarmResponse> getAllFarms() {
        return Collections.emptyList();
    }

    public ResponseEntity<String> createFarm(FarmRequest farmRequest) {
        farmRepository.save(FarmEntity.from(farmRequest));
        return null;
    }

    public ResponseEntity<String> updateFarm(FarmRequest farmRequest) {
        FarmEntity farmEntity = farmRepository.getById(1L);
        farmEntity.update(farmRequest);
        return null;
    }
}

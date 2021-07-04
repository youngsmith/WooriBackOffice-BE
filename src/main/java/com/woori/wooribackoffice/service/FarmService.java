package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.domain.dto.request.FarmRequest;
import com.woori.wooribackoffice.domain.dto.response.FarmResponse;
import com.woori.wooribackoffice.domain.entity.Farm;
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

    public FarmResponse getFarmById(final Long id) {
        return null;
    }

    public List<FarmResponse> getAllFarms() {
        return Collections.emptyList();
    }

    public ResponseEntity<String> createFarm(final FarmRequest farmRequest) {
        farmRepository.save(Farm.from(farmRequest));
        return null;
    }

    public ResponseEntity<String> updateFarm(final FarmRequest farmRequest) {
        Farm farm = farmRepository.getById(1L);
        farm.update(farmRequest);
        return null;
    }
}

package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.domain.dto.request.FarmRequest;
import com.woori.wooribackoffice.domain.dto.response.FarmResponse;
import com.woori.wooribackoffice.domain.entity.Farm;
import com.woori.wooribackoffice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;

    public FarmResponse getFarmById(final Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("농장 정보를 찾기 못했습니다."));
        return FarmResponse.from(farm);
    }

    public void createFarm(final FarmRequest farmRequest) {
        farmRepository.save(Farm.from(farmRequest));
    }

    public void updateFarm(final FarmRequest farmRequest) {
        Farm farm = farmRepository.findById(farmRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("농장 정보를 찾기 못했습니다."));
        farm.update(farmRequest);
        farmRepository.save(farm);
    }
}

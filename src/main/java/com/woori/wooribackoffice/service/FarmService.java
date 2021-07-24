package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.domain.dto.request.FarmRequest;
import com.woori.wooribackoffice.domain.dto.request.SearchParam;
import com.woori.wooribackoffice.domain.dto.response.FarmResponse;
import com.woori.wooribackoffice.domain.entity.Farm;
import com.woori.wooribackoffice.exception.ForeignKeyConstraintViolationException;
import com.woori.wooribackoffice.repository.FarmRepository;
import com.woori.wooribackoffice.repository.SelectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;
    private final SelectMapper selectMapper;

    public List<FarmResponse> getAllFarms() {
        return farmRepository.findAll()
                .stream().map(FarmResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<FarmResponse> searchFarm(String farmName) {
        // https://www.baeldung.com/spring-jpa-like-queries
        return farmRepository.findByNameContains(farmName)
                .stream().map(FarmResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public FarmResponse getFarmById(final Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("농장 정보를 찾을 수 없습니다."));
        return FarmResponse.from(farm);
    }

    public void createFarm(final FarmRequest farmRequest) {
        farmRepository.save(Farm.from(farmRequest));
    }

    public void updateFarm(final FarmRequest farmRequest) {
        Farm farm = farmRepository.findById(farmRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("농장 정보를 찾을 수 없습니다."));
        farm.update(farmRequest);
        farmRepository.save(farm);
    }

    public void deleteFarmById(final Long id) {
        if(selectMapper.isExaminationExistByFarmId(id)) {
            throw new ForeignKeyConstraintViolationException("해당 농장은 삭제할 수 없습니다.");
        }

        farmRepository.deleteById(id);
    }
}

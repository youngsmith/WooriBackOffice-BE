package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.entity.ExaminationEntity;
import com.woori.wooribackoffice.domain.entity.ExaminationCategoryEntity;
import com.woori.wooribackoffice.domain.entity.FarmEntity;
import com.woori.wooribackoffice.repository.ExaminationCategoryRepository;
import com.woori.wooribackoffice.repository.ExaminationRepository;
import com.woori.wooribackoffice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final FarmRepository farmRepository;
    private final ExaminationRepository examinationRepository;
    private final ExaminationCategoryRepository examinationCategoryRepository;

    @GetMapping("/test")
    @Transactional
    public void test() {
        FarmEntity farmEntity = new FarmEntity().address("강남구")
                .name("사랑농장")
                .owner("장영연");

        ExaminationEntity examinationEntity = new ExaminationEntity()
                .farmEntity(farmEntity);

        ExaminationCategoryEntity examinationCategoryEntity = new ExaminationCategoryEntity().name("피검사")
                .examinationEntity(examinationEntity);

        farmRepository.save(farmEntity);
        examinationRepository.save(examinationEntity);
        examinationCategoryRepository.save(examinationCategoryEntity);

    }

    @GetMapping("/update/{id}/{info}")
    @Transactional
    public void test2(@PathVariable("id") long id, @PathVariable("info") String info) {
        log.info("GetMapping (/update/{id}) ; id : {}", id);
//  사장님 이름 /
//        Examination examination = examinationRepository.getById(1L);
//        examination.farm(farmRepository.getById(2L));
        ExaminationEntity examinationEntity = examinationRepository.getById(1L);
        //examination.diagnosticResult(info);
        examinationEntity.registrationNumber(info);

        examinationCategoryRepository.deleteByExaminationId(1L);

        ExaminationCategoryEntity examinationCategoryEntity = new ExaminationCategoryEntity().name(info)
                .examinationEntity(examinationEntity);

        examinationCategoryRepository.save(examinationCategoryEntity);
    }


}

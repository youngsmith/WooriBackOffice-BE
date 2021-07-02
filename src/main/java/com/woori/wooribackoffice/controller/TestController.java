package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.entity.Examination;
import com.woori.wooribackoffice.domain.entity.ExaminationCategory;
import com.woori.wooribackoffice.domain.entity.Farm;
import com.woori.wooribackoffice.repository.ExaminationCategoryRepository;
import com.woori.wooribackoffice.repository.ExaminationRepository;
import com.woori.wooribackoffice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final FarmRepository farmRepository;
    private final ExaminationRepository examinationRepository;
    private final ExaminationCategoryRepository examinationCategoryRepository;

    @GetMapping("/test")
    @Transactional
    public void test() {
        Farm farm = new Farm().address("강남구")
                .name("사랑농장")
                .owner("장영연");

        Examination examination = new Examination().diagnosticResult("정상")
                .farm(farm);

        ExaminationCategory examinationCategory = new ExaminationCategory().name("피검사")
                .examination(examination);

        farmRepository.save(farm);
        examinationRepository.save(examination);
        //examinationCategoryRepository.save(examinationCategory);

    }
}

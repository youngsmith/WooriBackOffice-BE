package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.entity.Farm;
import com.woori.wooribackoffice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private FarmRepository farmRepository;

    @GetMapping("/test")
    public void test() {
        Farm farm = new Farm().address("강남구")
                .name("사랑농장")
                .owner("장영연");

        farmRepository.save(farm);
    }
}

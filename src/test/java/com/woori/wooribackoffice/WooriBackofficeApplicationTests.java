package com.woori.wooribackoffice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
class WooriBackofficeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void a() {
        List<Integer> arr = Arrays.asList(1,3,4,1,1,4,5,6,8,9,0);
        Set<Integer> st = arr.stream().collect(Collectors.toSet());

        for(Integer i : st) {
            System.out.println(i);
        }
    }

}

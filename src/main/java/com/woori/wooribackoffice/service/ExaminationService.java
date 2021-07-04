package com.woori.wooribackoffice.service;

import com.woori.wooribackoffice.repository.ExaminationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExaminationService {
    private final ExaminationRepository examinationRepository;

}

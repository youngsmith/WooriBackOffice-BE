package com.woori.wooribackoffice.repository;

import com.woori.wooribackoffice.domain.dto.request.SearchParam;
import com.woori.wooribackoffice.domain.dto.response.ExaminationResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SelectMapper {
    List<ExaminationResponse> searchExaminations(SearchParam searchParam);
    boolean examinationCategoryIsExistByCategoryId(Long categoryId);
    boolean examinationIsExistByFarmId(Long farmId);
}

package com.woori.wooribackoffice.repository;

import com.woori.wooribackoffice.domain.dto.response.ExaminationResponse;
import com.woori.wooribackoffice.domain.etc.SearchParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SelectMapper {
    List<ExaminationResponse> searchExaminations(SearchParam searchParam);
}

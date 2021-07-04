package com.woori.wooribackoffice.repository;

import com.woori.wooribackoffice.domain.entity.ExaminationCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationCategoryRepository extends JpaRepository<ExaminationCategoryEntity, Long> {
    long deleteByExaminationId(long id);
}

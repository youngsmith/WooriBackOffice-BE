package com.woori.wooribackoffice.repository;

import com.woori.wooribackoffice.domain.entity.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<FarmEntity, Long> {

}

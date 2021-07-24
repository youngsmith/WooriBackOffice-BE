package com.woori.wooribackoffice.repository;

import com.woori.wooribackoffice.domain.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    List<Farm> findByNameContains(String name);
}

package com.woori.wooribackoffice.repository;

import com.woori.wooribackoffice.domain.entity.CurrentToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrentTokenRepository extends JpaRepository<CurrentToken, Long> {
    long deleteByUserId(Long id);
    Optional<CurrentToken> findByUserId(Long id);
}

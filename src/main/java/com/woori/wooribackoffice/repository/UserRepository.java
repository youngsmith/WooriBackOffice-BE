package com.woori.wooribackoffice.repository;

import com.woori.wooribackoffice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteByUsername(String userName);

    boolean existsByUsername(String username);
}

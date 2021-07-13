package com.woori.wooribackoffice.security.repository;

import com.woori.wooribackoffice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    void deleteByUsername(String userName);

    boolean existsByUsername(String username);
}

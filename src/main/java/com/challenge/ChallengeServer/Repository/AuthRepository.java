package com.challenge.ChallengeServer.Repository;

import com.challenge.ChallengeServer.Domain.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByEmail(String email);
    Optional<Auth> findByName(String name);
}

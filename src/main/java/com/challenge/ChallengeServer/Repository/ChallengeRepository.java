package com.challenge.ChallengeServer.Repository;

import com.challenge.ChallengeServer.Domain.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}

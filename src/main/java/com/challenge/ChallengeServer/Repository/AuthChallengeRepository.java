package com.challenge.ChallengeServer.Repository;

import com.challenge.ChallengeServer.Domain.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

import java.util.List;

public interface AuthChallengeRepository extends JpaRepository<Challenge, Long> {

    List<Challenge> findByDate(LocalDate date);

    List<Challenge> findBySuccessTrue();

    List<Challenge> findBySuccessFalse();


}

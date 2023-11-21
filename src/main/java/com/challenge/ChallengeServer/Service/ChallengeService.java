package com.challenge.ChallengeServer.Service;

import com.challenge.ChallengeServer.Domain.entity.Challenge;
import com.challenge.ChallengeServer.Repository.AuthChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.List;

@Service
public class ChallengeService {

    private final AuthChallengeRepository challengeRepository;

    @Autowired
    public ChallengeService(AuthChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public List<Challenge> getChallengeByDate(LocalDate date) {
        return challengeRepository.findByDate(date);
    }

    public List<Challenge> getSuccessChallenges() {
        return challengeRepository.findBySuccessTrue();
    }

    public List<Challenge> getFailureChallenges() {
        return challengeRepository.findBySuccessFalse();
    }

    public List<Challenge> getAuthChallengesByDate(LocalDate date) {
        return challengeRepository.findByDate(date);
    }

}

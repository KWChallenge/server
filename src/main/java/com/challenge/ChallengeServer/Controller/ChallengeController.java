package com.challenge.ChallengeServer.Controller;

import com.challenge.ChallengeServer.Domain.entity.Challenge;
import com.challenge.ChallengeServer.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    // 모든 챌린지 목록 조회
    @GetMapping
    public List<Challenge> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    // 특정 날짜의 챌린지 목록 조회 (디테일뷰)
    @GetMapping("/challenge/{authId}/{challengeId}")
    public List<Challenge> getAuthChallengesByDate(@PathVariable LocalDate date) {
        return challengeService.getAuthChallengesByDate(date);
    }


    // 성공한 챌린지 목록 조회
    @GetMapping("/challenge/{authId}/sucess")
    public List<Challenge> getSuccessfulChallenges() {
        return challengeService.getSuccessChallenges();
    }

    // 실패한 챌린지 목록 조회
    @GetMapping("/challenge/{authId}/fail")
    public List<Challenge> getFailedChallenges() {
        return challengeService.getFailureChallenges();
    }

}

package com.challenge.ChallengeServer.Controller;
// 서연


import com.challenge.ChallengeServer.Domain.entity.Challenge;
import com.challenge.ChallengeServer.Service.ChallengeService;
import com.challenge.ChallengeServer.Service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
    private final MainService mainService;

    //생성자
    public MainController(MainService mainService){
        this.mainService = mainService;
    }

    //show server
    @GetMapping("/{authId}")
    public Challenge showChallenge(){
        return mainService.getChallengeOfTheDay();
    }
}

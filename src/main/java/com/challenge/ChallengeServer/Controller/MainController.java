package com.challenge.ChallengeServer.Controller;
// 서연


import com.challenge.ChallengeServer.Domain.entity.Challenge;
import com.challenge.ChallengeServer.Service.ChallengeService;
import com.challenge.ChallengeServer.Service.MainService;
import com.challenge.ChallengeServer.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Challenge> showChallenge(){
        Challenge challenge = mainService.getChallenge();
        return ResponseEntity.ok(challenge);
    }

    //ResponseEntity : HTTP 응답을 나타내는 클래스
    //BaseResponse : 클라이언트에게 성공 여부, 메시지 등 표준화된 형태의 응답 제공
    //사진 업로드
    @PostMapping("/{authId}/photo")
    public ResponseEntity<BaseResponse<Long>> uploadChallengePhoto(
            @PathVariable Long authId,
            @RequestParam String photo){
        Long challenge_Id = mainService.uploadPhoto(authId,photo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse<>(HttpStatus.OK.value(),challenge_Id));
    }
    //한줄평 업로드
    @PostMapping("/{authId}/review")
    public ResponseEntity<BaseResponse<Long>> uploadChallengeReview(
            @PathVariable Long authId,
            @RequestParam String review){
        Long challenge_Id = mainService.uploadReview(authId,review);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse<>(HttpStatus.OK.value(),challenge_Id));
    }
}

package com.challenge.ChallengeServer.Controller;
// 세연

import com.challenge.ChallengeServer.Domain.Dto.request.AuthRequestDto;
import com.challenge.ChallengeServer.Domain.Dto.request.LoginRequestDto;
import com.challenge.ChallengeServer.Exception.BadRequestException;
import com.challenge.ChallengeServer.Service.AuthService;
import com.challenge.ChallengeServer.common.BaseErrorResponse;
import com.challenge.ChallengeServer.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public BaseResponse signup(@RequestBody AuthRequestDto authRequestDto) {
        authService.join(authRequestDto);
        return new BaseResponse(HttpStatus.OK.value(), "회원가입이 완료되었습니다");
    }

    // 로그인
    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginRequestDto loginRequestDto) {
        return new BaseResponse(HttpStatus.OK.value(), "로그인 성공!",
                authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    BaseErrorResponse handleBadRequestException(Exception exception) {
        return new BaseErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}

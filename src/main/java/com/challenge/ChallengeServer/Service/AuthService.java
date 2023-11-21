package com.challenge.ChallengeServer.Service;

import com.challenge.ChallengeServer.Domain.Dto.request.AuthRequestDto;
import com.challenge.ChallengeServer.Domain.Dto.response.LoginResponse;
import com.challenge.ChallengeServer.Domain.entity.Auth;
import com.challenge.ChallengeServer.Exception.BadRequestException;
import com.challenge.ChallengeServer.Repository.AuthRepository;
import com.challenge.ChallengeServer.common.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    @Transactional
//    @Override
    public void join(AuthRequestDto authRequestDto) {
        validateUserRequest(authRequestDto);
        validateDuplicateEmail(authRequestDto.getEmail());
        authRepository.save(new Auth(authRequestDto));
    }

    @Transactional (readOnly = true)
//    @Override
    public LoginResponse login(String email, String password) {
        Auth auth = getAuth(email);
        auth.validatePassword(password);
        return new LoginResponse(auth.getName());
    }

    private void validateUserRequest(AuthRequestDto authRequestDto) {
        if(authRequestDto.getName() == null) throw new BadRequestException("이름을 입력하세요.");
        if(authRequestDto.getEmail() == null) throw new BadRequestException("이메일을 입력하세요.");
        if(authRequestDto.getPassword() == null) throw new BadRequestException("비밀번호를 입력하세요.");
    }

    private void validateDuplicateEmail(String email) {
        if(authRepository.findByEmail(email).stream().toList().size()>0) {
            throw new BadRequestException("이미 회원가입된 이메일입니다.");
        }
    }

    private Auth getAuth(String email) {
        return authRepository.findByEmail(email).stream()
                .findFirst()
                .orElseThrow(()->new BadRequestException("회원가입되지 않은 이메일"));
    }
}

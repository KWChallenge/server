package com.challenge.ChallengeServer.Repository;

import com.challenge.ChallengeServer.Domain.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    //findBy : 데이터를 조회할 때 사용
    //ChallengeId : 해당 challengeId 필드를 기준을 데이터를 조회
    //And : 여러개의 조건 연결 시 사용
    //Photo : photo 필드를 기준으로 데이터를 조회
    //Notnull: 없으면 반환값이 없음
    //Optional : 값이 존재할 수 있고 없을 수 도 있을 때 불러오는 거, 없을때도 있어서 이걸로 함
    //값이 감싸고, 값이 없으면 null 대신 Optional.empty()
    Optional<Challenge> findByChallenge_idAndPhotoNotNull(Long challenge_id);
}

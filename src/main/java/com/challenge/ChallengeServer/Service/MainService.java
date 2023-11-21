package com.challenge.ChallengeServer.Service;

import com.challenge.ChallengeServer.Domain.entity.Auth;
import com.challenge.ChallengeServer.Domain.entity.Challenge;
import com.challenge.ChallengeServer.Exception.BaseException;
import com.challenge.ChallengeServer.Repository.AuthRepository;
import com.challenge.ChallengeServer.Repository.ChallengeRepository;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
@Transactional
public class MainService {
    private final List<Challenge> challenges;
    private List<Integer> indexList;
    private int currentIndex = 0;
    private ChallengeRepository challengeRepository;
    private AuthRepository authRepository;

    //해당 challenge 리스트를 받아서 초기화 시키는 생성자
    public MainService(List<Challenge> challenges){
        this.challenges = challenges;
        this.indexList = new ArrayList<>();
        initialList();
    }
    /*
    public List<Challenge> getChallengesByAuth(Long auth_Id){
        return challengeRepository.findByAuth(auth_Id);
    }*/

    //*** show today's challenge ***
    //해당 리스트를 초기화해주는 메서드
    private void initialList(){
        for (int i = 0; i<challenges.size();i++){
            indexList.add(i);
        }
        //리스트를 섞어주는 역할
        Collections.shuffle(indexList);
        currentIndex = 0;
    }

    public void RandomList(List<Integer> indexList){
        Collections.shuffle(indexList);
    }

    //해당 리스트의 랜덤으로 최신 목록을 가져오는 것 (getter)
    //오늘의 challenge 보여주기
    public Challenge getChallengeOfTheDay(int currentIndex){
        int intdex = indexList.get(currentIndex);

        //1을 늘렸는데, indexList.size보다 크면 끝까지 돈거라서
        //다시 랜덤으로 바꾸고 currentIndex를 0으로 초기화
        if(currentIndex++ >= indexList.size()) {
            //Collections.shuffle(indexList);
            RandomList(indexList);
            currentIndex = 0;
        }

        return challenges.get(intdex);
    }   
    
    //Getter 함수
    public Challenge getChallenge(){
        return getChallengeOfTheDay(currentIndex);
    }

    //자정마다 실행되도록 (직접 하면 실행이 안되서)
    @Scheduled(cron = "0 0 0 * * *")
    private void update(){
        getChallengeOfTheDay(currentIndex);
        currentIndex ++;
    }

    //*** Save Photo And Get ***
    //사진 업로드
    public Long uploadPhoto(Long auth_id, String photo){
        //해당 엔티티를 가져옴
        Challenge today = getChallenge();

        //auth_id를 기반으로 사용자 찾기
        Optional<Auth> optionalAuth = authRepository.findById(auth_id);
        Auth auth = optionalAuth.orElseThrow(()
        -> new BaseException(404, "Auth not found"));

        //이제 엔티티에 대한 사진을 set해야함
        //set 메서드만 호출한다면 해당 엔티티는 JPA에서 관리되지 않아서 관리하도록 repository에 올림

        //챌린지에 사용자를 설정
        today.setAuth(auth);
        //업로드 사진
        today.setPhoto(photo);
        auth.addChallenge(today);

        //DB에 저장하기 위함
        challengeRepository.save(today);
        authRepository.save(auth);
        return today.getChallenge_id();
    }

    public Long uploadReview(Long auth_id, String review){
        //해당 엔티티를 가져옴
        Challenge today = getChallenge();

        //auth_id를 기반으로 사용자 찾기
        Optional<Auth> optionalAuth = authRepository.findById(auth_id);
        Auth auth = optionalAuth.orElseThrow(()
                -> new BaseException(404, "Auth not found"));

        //이제 엔티티에 대한 사진을 set해야함
        //set 메서드만 호출한다면 해당 엔티티는 JPA에서 관리되지 않아서 관리하도록 repository에 올림

        //챌린지에 사용자를 설정
        today.setAuth(auth);
        //업로드 사진
        today.setReview(review);
        auth.addChallenge(today);

        //DB에 저장하기 위함
        challengeRepository.save(today);
        authRepository.save(auth);
        return today.getChallenge_id();
    }
}

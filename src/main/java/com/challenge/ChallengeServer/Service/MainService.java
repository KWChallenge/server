package com.challenge.ChallengeServer.Service;

import com.challenge.ChallengeServer.Domain.entity.Challenge;
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
    private int currentIndex;
    private ChallengeRepository challengeRepository;

    //해당 challenge 리스트를 받아서 초기화 시키는 생성자
    public MainService(List<Challenge> challenges){
        this.challenges = challenges;
        this.indexList = new ArrayList<>();
        initialList();
    }

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

    //해당 리스트의 랜덤으로 최신 목록 불러오기
    public Challenge getChallengeOfTheDay(){
        int intdex = indexList.get(currentIndex);
        currentIndex++;

        //1을 늘렸는데, indexList.size보다 크면 끝까지 돈거라서
        //다시 랜덤으로 바꾸고 currentIndex를 0으로 초기화
        if(currentIndex >= indexList.size()) {
            Collections.shuffle(indexList);
            currentIndex = 0;
        }

        return challenges.get(intdex);
    }

    //자정마다 실행되도록(직접 하면 실행이 안되서)
    @Scheduled(cron = "0 0 0 * * *")
    private void update(){
        getChallengeOfTheDay();
    }

    //*** Save Photo And Get ***
    //사진 업로드
    public void uploadPhoto(Long challenge_id, String photo){
        //엔티티를 조회(챌린지를 조회) -> 즉 challengeId 를 할 수 있는 엔티티를 조회하기
        Optional<Challenge> optionalChallenge = challengeRepository.findById(challenge_id);

        //이제 엔티티에 대한 사진을 set해야함
        //set 메서드만 호출한다면 해당 엔티티는 JPA에서 관리되지 않아서 관리하도록 repository에 올림
        optionalChallenge.ifPresent(challenge -> {
            challenge.setPhoto(photo);
            challengeRepository.save(challenge); //DB에 저장하기 위함
        });
    }



}

package com.challenge.ChallengeServer.Service;

import com.challenge.ChallengeServer.Domain.entity.Challenge;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class MainService {
    private final List<Challenge> challenges;
    private List<Integer> indexList;
    private int currentIndex;

    //해당 challenge 리스트를 받아서 초기화 시키는 생성자
    public MainService(List<Challenge> challenges){
        this.challenges = challenges;
        this.indexList = new ArrayList<>();
        initialList();
    }

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


}

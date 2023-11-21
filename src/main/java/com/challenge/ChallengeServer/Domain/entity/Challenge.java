package com.challenge.ChallengeServer.Domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Challenge extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challenge_id;

    @Column(nullable = false)
    private String challenge_name;

    @Column(nullable = false)
    private LocalDate date;

    //null = false 는 항상 값을 가져와야하기 때문에
    //안뜨게 하는게 좋음

    @Column
    private String photo;

    @Column
    private String review;

    @Column(nullable = false)
    private Boolean success;

    @ManyToOne
    @JoinColumn(name = "auth_id")
    private Auth auth;

    @Builder
    public Challenge(String challenge_name, LocalDate date,
                     String photo, String review, Boolean success) {
        this.challenge_name = challenge_name;
        this.date = date;
        this.photo = photo;
        this.review = review;
        this.success = success;
    }

    public Auth getAuth(){
        return auth;
    }

    public void  setAuth(Auth auth){
        this.auth = auth;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }
    public void setReview(String review){this.review = review; }
}

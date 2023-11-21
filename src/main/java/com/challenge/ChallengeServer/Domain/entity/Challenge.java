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
    private String icon;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private String photo;

    @Column
    private String review;

    @Column(nullable = false)
    private Boolean success;



    @Builder
    public Challenge(String challenge_name, String icon, LocalDate date,
                     String photo, String review, Boolean success) {
        this.challenge_name = challenge_name;
        this.icon = icon;
        this.date = date;
        this.photo = photo;
        this.review = review;
        this.success = success;
    }
}

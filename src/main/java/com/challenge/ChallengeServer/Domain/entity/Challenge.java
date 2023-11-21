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

    @Column(nullable = false)
    private String photoName;

    @Column(nullable = false)
    private String photoPath;

    //???
    @Column(nullable = false)
    private  String origPhotoname;

    @Column
    private String review;

    @Column(nullable = false)
    private Boolean success;

    @Builder
    public Challenge(String challenge_name, LocalDate date,
                     String photoName, String photoPath, String origPhotoname,
                     String review, Boolean success) {
        this.challenge_name = challenge_name;
        this.date = date;
        this.photoName = photoName;
        this.photoPath = photoPath;
        this.origPhotoname = origPhotoname;
        this.review = review;
        this.success = success;
    }
}

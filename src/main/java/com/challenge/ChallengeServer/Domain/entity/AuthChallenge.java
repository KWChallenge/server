package com.challenge.ChallengeServer.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AuthChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auth_challenge_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_id")
    private Auth auth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge_id;


    @Column(name = "challenge_name")
    private String challenge_name;


    @Column(name = "success")
    private boolean success;


    @Column(name = "date")
    private String date;



    public AuthChallenge(String date, String challenge_name, boolean success){
        this.date = date;
        this.challenge_name = challenge_name;
        this.success = success;
    }
}

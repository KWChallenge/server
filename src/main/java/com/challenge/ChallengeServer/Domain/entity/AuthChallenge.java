package com.challenge.ChallengeServer.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Challenge challenge;
}

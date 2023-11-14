package com.challenge.ChallengeServer.Domain.entity;

import com.challenge.ChallengeServer.Domain.Dto.request.AuthRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Auth extends Time{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean agreement;

    @OneToMany(mappedBy = "auth", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<AuthChallenge> authChallenges = new ArrayList<>();

    @Builder
    public Auth(String name, String email, String password, boolean agreement) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.agreement = agreement;
    }

    public Auth(AuthRequestDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }
}

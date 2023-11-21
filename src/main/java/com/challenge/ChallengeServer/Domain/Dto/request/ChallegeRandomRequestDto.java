package com.challenge.ChallengeServer.Domain.Dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@Setter
//요청변수
public class ChallegeRandomRequestDto {
    private Long challege_id;
    private LocalDate date;
}

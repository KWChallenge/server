package com.challenge.ChallengeServer.Exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) { super(msg); }
}

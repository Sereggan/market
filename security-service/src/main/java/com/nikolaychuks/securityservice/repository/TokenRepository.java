package com.nikolaychuks.securityservice.repository;

import com.nikolaychuks.securityservice.model.Token;

import java.util.Optional;

public interface TokenRepository {
    public Optional<Token> findByUserId(String userId);
    public void put(Token token);

    void deleteById(String clientId);
}

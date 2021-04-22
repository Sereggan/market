package com.nikolaychuks.securityservice.repository;

import com.nikolaychuks.securityservice.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TokenRepositoryImpl implements TokenRepository {

    private final static String KEY = "TOKEN";
    private final RedisTemplate<String, Token> redisTemplate;
    private HashOperations hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Optional<Token> findByUserId(String userId) {
        return Optional.ofNullable((Token) hashOperations.get(KEY, userId));
    }


    public void put(Token token) {
        hashOperations.put(KEY, token.getUserId(), token.getToken());
    }

    @Override
    public void deleteById(String clientId) {
        hashOperations.delete(KEY, clientId);
    }
}

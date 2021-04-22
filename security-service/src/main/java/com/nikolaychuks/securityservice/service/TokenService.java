package com.nikolaychuks.securityservice.service;

import com.nikolaychuks.securityservice.controller.advisor.CustomAuthException;
import com.nikolaychuks.securityservice.model.Token;
import com.nikolaychuks.securityservice.model.User;
import com.nikolaychuks.securityservice.model.VerifyTokenRequest;
import com.nikolaychuks.securityservice.repository.TokenRepository;
import com.nikolaychuks.securityservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.Date;
import java.util.Objects;

@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    public void validateToken(VerifyTokenRequest tokenRequest) throws AuthException {
        User user = userRepository.findById(tokenRequest.getClientId()).orElseThrow(() -> {
            throw new CustomAuthException();
        });

        Token token = tokenRepository.findByUserId(tokenRequest.getClientId()).orElseThrow(() -> {
            throw new CustomAuthException();
        });

        if (!Objects.equals(token.getToken(), tokenRequest.getToken())) {
            tokenRepository.deleteById(tokenRequest.getClientId());
            throw new CustomAuthException();
        }

    }

    public String generateToken(String userId) {
        Token token = Token.builder().userId(userId)
                .token(Jwts.builder().setSubject(userId).setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, "SUPER_SECRET_KEY").compact()).build();
        tokenRepository.put(token);
        return token.getToken();
    }

}

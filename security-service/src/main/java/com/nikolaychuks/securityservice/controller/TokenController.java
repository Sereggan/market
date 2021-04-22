package com.nikolaychuks.securityservice.controller;

import com.nikolaychuks.securityservice.model.VerifyTokenRequest;
import com.nikolaychuks.securityservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("/security/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @PostMapping("/check")
    public ResponseEntity checkToken(@RequestBody VerifyTokenRequest tokenRequest) throws AuthException {
        tokenService.validateToken(tokenRequest);
        return ResponseEntity.ok("Token is valid");
    }

    @PostMapping("/generate")
    public ResponseEntity generateToken(){
        return ResponseEntity.ok("generated");
    }
}

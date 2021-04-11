package com.nikolaychuks.securityservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security/token")
public class TokenController {

    @PostMapping("/check")
    public ResponseEntity checkToken(){
        return ResponseEntity.ok("HEH");
    }

    @PostMapping("/generate")
    public ResponseEntity generateToken(){
        return ResponseEntity.ok("generated");
    }
}

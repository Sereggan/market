package com.nikolaychuks.securityservice.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VerifyTokenRequest {

    private String token;
    private String clientId;
}

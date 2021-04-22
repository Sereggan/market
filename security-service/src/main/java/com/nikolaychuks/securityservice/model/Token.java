package com.nikolaychuks.securityservice.model;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@RedisHash("token")
public class Token implements Serializable {
    private String userId;
    private String token;
}

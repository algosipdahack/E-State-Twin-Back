package com.example.Estate_Twin.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate redisTemplate;

    // 키-벨류 설정
    public void setValues(String token, String email) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(email, token, Duration.ofDays(7)); // 7일 뒤 메모리에서 삭제된다.
    }

    // 키값으로 벨류 가져오기
    public String getValues(String email) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(email);
    }

    //키 밸류 삭제
    public void delValues(String email) {
        String token = getValues(email);
        redisTemplate.delete(token.substring(7));
    }
}

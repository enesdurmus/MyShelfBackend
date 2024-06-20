package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.consts.CacheNames;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.exceptions.TooManyRequestException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;

@Service
@AllArgsConstructor
public class RateLimitService {
    private static final int DEFAULT_MAX_REQUESTS_PER_WINDOW = 100;
    private static final int RATE_LIMIT_WINDOW_SECONDS = 60;
    private static final String RATE_LIMIT_EXPIRE_POSTFIX = "::expires";
    private static final Logger logger = LoggerFactory.getLogger(RateLimitService.class);
    private final RedisTemplate<Object, Object> redisTemplate;

    public void deleteExpiredRateLimits() {
        try {
            Instant currentUtc = Instant.now();
            ArrayList<String> expiredKeys = new ArrayList<>();

            ScanOptions options = ScanOptions.scanOptions().match("*" + RATE_LIMIT_EXPIRE_POSTFIX).build();
            Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(CacheNames.RATE_LIMIT_HASH_KEY, options);

            while (cursor.hasNext()) {
                Map.Entry<Object, Object> entry = cursor.next();
                Instant expiresAt = Instant.parse(entry.getValue().toString());
                if (currentUtc.isAfter(expiresAt)) {
                    expiredKeys.add(entry.getKey().toString());
                    expiredKeys.add(entry.getKey().toString().split("::")[0]);
                }
            }

            cursor.close();

            if (expiredKeys.size() > 0) {
                redisTemplate.opsForHash().delete(CacheNames.RATE_LIMIT_HASH_KEY, expiredKeys.toArray());
                logger.info("Expired rate limits keys deleted count: {}", expiredKeys.size());
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }

    public void checkRateLimit(User user) {
        String hashKey = determineHashKey(user);
        Long currentCount = redisTemplate.opsForHash().increment(CacheNames.RATE_LIMIT_HASH_KEY, hashKey, 1);

        if (currentCount == 1) {
            redisTemplate.opsForHash().put(CacheNames.RATE_LIMIT_HASH_KEY, hashKey + RATE_LIMIT_EXPIRE_POSTFIX, Instant.now().plus(Duration.ofSeconds(RATE_LIMIT_WINDOW_SECONDS)).toString());
        }

        if (currentCount > DEFAULT_MAX_REQUESTS_PER_WINDOW) {
            throw new TooManyRequestException("Too many request");
        }
    }

    private String determineHashKey(User user) {
        return user.getId().toString();
    }
}
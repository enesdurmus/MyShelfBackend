package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.consts.CacheNames;
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
    private static final int MAX_REQUESTS_PER_MINUTE = 100;
    private static final int RATE_LIMIT_WINDOW_SECONDS = 60;
    private static final String RATE_LIMIT_EXPIRE_POST_FIX = "::expires";
    private static final Logger logger = LoggerFactory.getLogger(RateLimitService.class);
    private final RedisTemplate<Object, Object> redisTemplate;

    public void deleteExpiredRateLimits() {
        try {
            Instant currentUtc = Instant.now();
            ArrayList<String> expiredKeys = new ArrayList<>();

            ScanOptions options = ScanOptions.scanOptions().match("*" + RATE_LIMIT_EXPIRE_POST_FIX).build();
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

    public void checkRateLimit(String ipAddress) {
        long currentCount = redisTemplate.opsForHash().increment(CacheNames.RATE_LIMIT_HASH_KEY, ipAddress, 1);

        if (currentCount == 1) {
            redisTemplate.opsForHash().put(CacheNames.RATE_LIMIT_HASH_KEY, ipAddress +
                    RATE_LIMIT_EXPIRE_POST_FIX, Instant.now().plus(Duration.ofSeconds(RATE_LIMIT_WINDOW_SECONDS)).toString());
        }

        if (currentCount > MAX_REQUESTS_PER_MINUTE) {
            // throw new TooManyRequestException();
        }
    }
}
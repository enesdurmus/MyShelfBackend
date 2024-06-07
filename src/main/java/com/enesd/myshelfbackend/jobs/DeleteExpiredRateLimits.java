package com.enesd.myshelfbackend.jobs;

import com.enesd.myshelfbackend.services.RateLimitService;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteExpiredRateLimits {

    private final RateLimitService rateLimitService;

    @Scheduled(fixedRateString = "60000")
    @SchedulerLock(name = "DeleteExpiredRateLimits.deleteExpiredRateLimits", lockAtLeastFor = "PT15S", lockAtMostFor = "PT30S")
    public void deleteExpiredRateLimits() {
        rateLimitService.deleteExpiredRateLimits();
    }
}

package com.enesd.myshelfbackend.jobs;

import com.enesd.myshelfbackend.service.RateLimitService;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteExpiredRateLimits {

    private final RateLimitService rateLimitService;

    @Scheduled(cron = "0 * * * * *") // Every minute
    @SchedulerLock(name = "DeleteExpiredRateLimits.deleteExpiredRateLimits", lockAtLeastFor = "PT15S", lockAtMostFor = "PT30S")
    public void deleteExpiredRateLimits() {
        rateLimitService.deleteExpiredRateLimits();
    }
}

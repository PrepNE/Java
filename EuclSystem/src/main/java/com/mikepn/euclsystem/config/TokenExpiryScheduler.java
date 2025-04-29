package com.mikepn.euclsystem.config;

import com.mikepn.euclsystem.services.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenExpiryScheduler {

    private final INotificationService notificationService;


    @Scheduled(cron = "*/30 * * * * *")
    public void runNotificationJob() {
        log.info("🔔 Token notification job triggered at {}", LocalDateTime.now());
        notificationService.checkExpiringTokens();
    }

}

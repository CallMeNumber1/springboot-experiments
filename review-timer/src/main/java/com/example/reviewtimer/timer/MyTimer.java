package com.example.reviewtimer.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyTimer {
    @Scheduled(cron = "0 55 22 1 6 ?")
    public void test() {
        log.debug("------look at me-------");
    }
}

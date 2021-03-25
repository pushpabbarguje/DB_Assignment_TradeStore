package com.db.assignment.tradestore.tasks;


import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.assignment.tradestore.tasks.TradeScheduledTask;

import org.awaitility.Duration;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeScheduledTaskTest {
	@SpyBean 
    private TradeScheduledTask tradeScheduledTask;

    @Test
    public void whenWaitOneSecond_thenScheduledIsCalledAtLeastTenTimes() {
        await().atMost(Duration.ONE_MINUTE)
        .untilAsserted(() -> verify(tradeScheduledTask,atLeast(2)).updateExpireFlag());
    }
}

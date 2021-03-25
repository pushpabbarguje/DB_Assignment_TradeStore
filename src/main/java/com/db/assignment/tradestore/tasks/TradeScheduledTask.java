package com.db.assignment.tradestore.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.db.assignment.tradestore.service.TradeStoreService;

@Component
public class TradeScheduledTask {
	
	@Autowired
	TradeStoreService tradeStoreService;
	
	@Scheduled(cron = "${cron.expression}")
	public void updateExpireFlag(){
		tradeStoreService.updateTradeExpireFlag();
		System.out.println("Scheduler started");
	}
}

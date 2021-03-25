package com.db.assignment.tradestore.service;

import com.db.assignment.tradestore.dao.TradeStore;

public interface TradeStoreService {
	
	public boolean validateTrade(TradeStore tradeStore);
	public void addTradeToStore(TradeStore tradeStore);
	public void findAll();
	public void updateTradeExpireFlag();

}

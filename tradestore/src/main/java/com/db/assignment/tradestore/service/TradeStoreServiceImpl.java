package com.db.assignment.tradestore.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.assignment.tradestore.dao.TradeStore;
import com.db.assignment.tradestore.dao.TradeStoreRepository;

@Service
@Transactional
public class TradeStoreServiceImpl implements TradeStoreService{
	
	@Autowired
	TradeStoreRepository tradeStoreRepository;
	
	/**
	 * validate Trade return true if  maturityDate > currentDate and version > exitingVersion
	 */
	@Override
	public boolean validateTrade(TradeStore tradeStore) {
		if(validateMaturityDate(tradeStore)){
			Optional<TradeStore> tradeStoreFromDb = tradeStoreRepository.findById(tradeStore.getTradeId());
			if(tradeStoreFromDb.isPresent() && tradeStore.getVersion() < tradeStoreFromDb.get().getVersion()){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * true - If maturityDate > currentDate
	 * @param tradeStore
	 * @return
	 */
	private boolean validateMaturityDate(TradeStore tradeStore) {
		return !tradeStore.getMaturityDate().isBefore(LocalDate.now());
	}

	
	/**
	 * Add/update the exiting with new one
	 */
	@Override
	public void addTradeToStore(TradeStore tradeStore) {
		tradeStore.setCreatedDate(LocalDate.now());
		tradeStoreRepository.save(tradeStore);
	}

	/**
	 * Find all trades 
	 */
	@Override
	public void findAll() {
		System.out.println("Find All: "+tradeStoreRepository.findAll());
	}

	
	/**
	 * This method will get called from Scheduled task
	 * Updates the expireFlag to 'Y' if maturityDate < currentDate
	 */
	@Override
	public void updateTradeExpireFlag() {
		tradeStoreRepository.findAll().stream().forEach(t -> {
			if(!validateMaturityDate(t)){
				t.setExpired('Y');
				tradeStoreRepository.save(t);
			}
			
		});
	}

	
}

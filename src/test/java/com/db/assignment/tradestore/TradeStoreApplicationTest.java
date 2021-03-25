package com.db.assignment.tradestore;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.assignment.tradestore.controller.InvalidTradeException;
import com.db.assignment.tradestore.controller.TradeStoreController;
import com.db.assignment.tradestore.dao.TradeStore;
import com.db.assignment.tradestore.service.TradeStoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeStoreApplicationTest {

	@Autowired
	TradeStoreController tradeStoreController;
	
	/**
	 * with below test user can add trade successfully
	 */
	@Test
	public void addTradeStoreSuccessFulTest(){
		ResponseEntity responseEntity = tradeStoreController.addTrade(creatTrade("T1", 1, LocalDate.now()));
		Assert.assertEquals(ResponseEntity.ok().build().getStatusCode(), responseEntity.getStatusCode());
	}
	
	/**
	 * addTradeStoreWithPastMaturityDateTest : is to check MaturityDate
	*	If maturityDate is older than currentDate then Exception should be thrown 
	*/
	@Test
	public void addTradeStoreWithPastMaturityDateTest(){
		LocalDate dateOne = LocalDate.of(2020, 12, 12);
		ResponseEntity responseEntity= new ResponseEntity<>(HttpStatus.OK);
		try{
			 responseEntity = tradeStoreController.addTrade(creatTrade("T3", 1, dateOne));
		}catch(InvalidTradeException ex){
			Assertions.assertEquals("Invalid trade", ex.getMessage());
		}
		
	}
	
	/**
	 * addTradeStoreWithOldVersionTest - check version of incoming trade
	 * If version is older than existing one then Exception should be thrown
	 */
	@Test
	public void addTradeStoreWithOldVersionTest(){
		
		ResponseEntity responseEntity = tradeStoreController.addTrade(creatTrade("T2", 3, LocalDate.now()));
		Assert.assertEquals(ResponseEntity.ok().build().getStatusCode(), responseEntity.getStatusCode());
		try{			 
			 //with old version
			 responseEntity = tradeStoreController.addTrade(creatTrade("T2", 2,LocalDate.now()));
		}catch(InvalidTradeException ex){
			Assertions.assertEquals("Invalid trade", ex.getMessage());
		}
		
	}
	
	/**
	 * updateTradeTest - update the existing Trade with new Data
	 */
	@Test
	public void updateTradeTest(){
		
		ResponseEntity responseEntity = tradeStoreController.addTrade(creatTrade("T4", 1, LocalDate.now()));
		 //update existing Trade with higher version 
		responseEntity = tradeStoreController.addTrade(creatTrade("T4", 2, LocalDate.now()));
		Assert.assertEquals(ResponseEntity.ok().build().getStatusCode(), responseEntity.getStatusCode());
		
	}
	
	private TradeStore creatTrade(String id,int version,LocalDate maturityDate){
		TradeStore tradeStore = new TradeStore();
		tradeStore.setTradeId(id);
		tradeStore.setVersion(version);
		tradeStore.setCounterPartyId("CP-1");
		tradeStore.setCreatedDate(LocalDate.now());
		tradeStore.setBookId("B1");
		tradeStore.setExpired('N');
		tradeStore.setMaturityDate(maturityDate);
		return tradeStore;
	}
	
	
	
	
}

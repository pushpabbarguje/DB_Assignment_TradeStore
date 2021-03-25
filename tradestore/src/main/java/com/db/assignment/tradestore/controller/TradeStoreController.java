package com.db.assignment.tradestore.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.assignment.tradestore.dao.TradeStore;
import com.db.assignment.tradestore.service.TradeStoreService;

@RestController
@RequestMapping("/trade")
public class TradeStoreController {
	
	@Autowired
	TradeStoreService tradeStoreService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> testHello() {
		return ResponseEntity.ok("Hi Gaurav");
				
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addTrade(@RequestBody TradeStore tradeStore) {
		// validate the trade - maturityDate > currentDate and version > exitingVersion
		if(tradeStoreService.validateTrade(tradeStore)){
			//add the trade to TradeStore or update the existing one with new one
			tradeStoreService.addTradeToStore(tradeStore);
			tradeStoreService.findAll();
		}else{
			throw new InvalidTradeException("Invalid trade");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}

	
}

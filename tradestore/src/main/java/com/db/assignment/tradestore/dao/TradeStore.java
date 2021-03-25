package com.db.assignment.tradestore.dao;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TRADE_STORE")
public class TradeStore {
	
	@Id
	@Column(name="TRADE_ID")
	private String tradeId;
	
	@Column(name="VERSION")
	private int version;
	
	@Column(name ="COUNTER_PARTY_ID")
	private String counterPartyId;
	
	@Column(name="BOOK_ID")
	private String bookId;
	
	@Column(name="MATURITY_DATE")
	private LocalDate maturityDate;
	
	@Column(name="CREATED_DATE")
	private LocalDate createdDate;
	
	@Column(name="EXPIRED")
	private char expired;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}


	public char getExpired() {
		return expired;
	}

	public void setExpired(char expired) {
		this.expired = expired;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeStore [tradeId=");
		builder.append(tradeId);
		builder.append(", version=");
		builder.append(version);
		builder.append(", counterPartyId=");
		builder.append(counterPartyId);
		builder.append(", bookId=");
		builder.append(bookId);
		builder.append(", maturityDate=");
		builder.append(maturityDate);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", expired=");
		builder.append(expired);
		builder.append("]");
		return builder.toString();
	}

	
	
}

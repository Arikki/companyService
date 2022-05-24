package com.estockMarket.companyService.events;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CompanyCreatedEvent {
	private String uuid;
	private String companyCode;
	private String companyName;
	private String ceoName;
	private BigDecimal turnOver;
	private String website;
	private String enlistedStockMarkets;
}

package com.estockMarket.companyService.command.api.commands;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.axonframework.modelling.command.TargetAggregateIdentifier;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CompanyCreateCommand {

	@TargetAggregateIdentifier
	private String uuid;
	private String companyCode;
	private String companyName;
	private String ceoName;
	private BigDecimal turnOver;
	private String website;
	private String enlistedStockMarkets;
}

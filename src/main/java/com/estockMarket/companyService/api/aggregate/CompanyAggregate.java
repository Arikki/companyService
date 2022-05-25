package com.estockMarket.companyService.api.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.estockMarket.companyService.command.api.commands.CompanyCreateCommand;
import com.estockMarket.companyService.command.api.commands.DeleteCompanyCommand;
import com.estockMarket.companyService.events.CompanyCreatedEvent;
import com.estockMarket.companyService.events.CompanyDeletedEvent;

@Aggregate
public class CompanyAggregate {
	
	@AggregateIdentifier
	private String uuid;
	private String companyCode;
	private String companyName;
	private String ceoName;
	private BigDecimal turnOver;
	private String website;
	private String enlistedStockMarkets;
	
	
	public CompanyAggregate() {
		
	}
	
	@CommandHandler
	public CompanyAggregate(CompanyCreateCommand cmd) {
		
		CompanyCreatedEvent event = CompanyCreatedEvent.builder()
										.ceoName(cmd.getCeoName())
										.companyCode(cmd.getCompanyCode())
										.companyName(cmd.getCompanyName())
										.enlistedStockMarkets(cmd.getEnlistedStockMarkets())
										.turnOver(cmd.getTurnOver())
										.website(cmd.getWebsite())
										.uuid(cmd.getUuid())
										.build();
		
		AggregateLifecycle.apply(event);
	}

	
	@CommandHandler
	public CompanyAggregate(DeleteCompanyCommand cmd) {
		CompanyDeletedEvent event = CompanyDeletedEvent.builder()
										.uuid(cmd.getUuid())
										.companyCode(cmd.getCompanyCode())
										.build();
		AggregateLifecycle.apply(event);
		
	}
	
	@EventSourcingHandler
	public void on(CompanyCreatedEvent event) {
		this.ceoName = event.getCeoName();
		this.companyCode = event.getCompanyCode();
		this.companyName  = event.getCompanyName();
		this.enlistedStockMarkets = event.getEnlistedStockMarkets();
		this.turnOver = event.getTurnOver();
		this.website = event.getWebsite();
		this.uuid = event.getUuid();
	}
	
	@EventSourcingHandler
	public void on(CompanyDeletedEvent event) {
		this.companyCode = event.getCompanyCode();
		this.uuid = event.getUuid();
	}
	
}

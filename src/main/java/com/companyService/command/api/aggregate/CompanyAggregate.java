package com.companyService.command.api.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.companyService.command.api.commands.CreateCompanyCommand;
import com.companyService.command.api.commands.DeleteCompanyCommand;
import com.companyService.command.api.event.CompanyCreatedEvent;
import com.companyService.command.api.event.CompanyDeletedEvent;


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
	public CompanyAggregate(CreateCompanyCommand createCompanyCommand) {
		
		CompanyCreatedEvent companyCreatedEvent = CompanyCreatedEvent.builder().uuid(createCompanyCommand.getUuid())
																			.companyCode(createCompanyCommand.getCompanyCode())
																			.companyName(createCompanyCommand.getCompanyName())
																			.ceoName(createCompanyCommand.getCeoName())
																			.website(createCompanyCommand.getWebsite())
																			.turnOver(createCompanyCommand.getTurnOver())
																			.enlistedStockMarkets(createCompanyCommand.getEnlistedStockMarkets())
																			   .build();
		AggregateLifecycle.apply(companyCreatedEvent);
	}
	
	@CommandHandler
	public CompanyAggregate (DeleteCompanyCommand deleteCompanyCommand) {
		
		CompanyDeletedEvent companyDeletedEvent = CompanyDeletedEvent.builder()
														.uuid(deleteCompanyCommand.getUuid())
														.companyCode(deleteCompanyCommand.getCompanyCode())
														.build();
		
		AggregateLifecycle.apply(companyDeletedEvent);
		
	}
	
	@EventSourcingHandler
	public void on(CompanyCreatedEvent companyCreatedEvent) {

		this.uuid = companyCreatedEvent.getUuid();
		this.companyCode = companyCreatedEvent.getCompanyCode();
		this.companyName = companyCreatedEvent.getCompanyName();
		this.ceoName = companyCreatedEvent.getCeoName();
		this.turnOver = companyCreatedEvent.getTurnOver();
		this.website = companyCreatedEvent.getWebsite();
		this.enlistedStockMarkets = companyCreatedEvent.getEnlistedStockMarkets();

	}
	
	@EventSourcingHandler
	public void on(CompanyDeletedEvent companyDeletedEvent) {
		this.uuid = companyDeletedEvent.getUuid();
		this.companyCode = companyDeletedEvent.getCompanyCode();
	}
	
}

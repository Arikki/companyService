package com.companyService.command.api.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companyService.command.api.commands.CreateCompanyCommand;
import com.companyService.command.api.commands.DeleteCompanyCommand;
import com.companyService.model.CompanyWriteModel;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1.0/market/company/")
@Tag(name = "Company API", description = "API to add/delete a company")
public class CompanyCommandController {
	
private static final Logger logger = LoggerFactory.getLogger(CompanyCommandController.class);
	
private CommandGateway commandGateway;

public CompanyCommandController(CommandGateway commandGateway) {
	this.commandGateway = commandGateway;
}

	@PostMapping("register")
//	@Operation(summary = "Register a new company", security = @SecurityRequirement(name = "bearerAuth"))
	@Operation(summary = "Register a new company")
	public String registerProfile(@Valid @RequestBody CompanyWriteModel company) {
		
		logger.info("Request to add company" + company.toString());
		
		CreateCompanyCommand createCompanyCommand = CreateCompanyCommand.builder()
													.uuid(UUID.randomUUID().toString())
													.companyCode(company.getCompanyCode())
													.companyName(company.getCompanyName())
													.ceoName(company.getCeoName())
													.turnOver(company.getTurnOver())
													.website(company.getWebsite())
													.enlistedStockMarkets(company.getEnlistedStockMarkets())
													.build();
		

		logger.info("Created command to add a stock with company code:" + company.getCompanyCode());
		String result = commandGateway.sendAndWait(createCompanyCommand);
		return result;
	}

	@DeleteMapping("delete/{companyCode}")
	@Operation(summary = "Deletes a company")
	public String deleteCompanyByCode(@PathVariable String companyCode) {
		logger.info("Request to delete a company with code " + companyCode);
		
		DeleteCompanyCommand deleteCompanyCommand  = DeleteCompanyCommand.builder()
																		.companyCode(companyCode)
																		.uuid(UUID.randomUUID().toString())
																		.build();
		return commandGateway.sendAndWait(deleteCompanyCommand);
	}
}

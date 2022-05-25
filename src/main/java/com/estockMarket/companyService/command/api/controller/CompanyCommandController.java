package com.estockMarket.companyService.command.api.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.estockMarket.companyService.command.api.commands.CompanyCreateCommand;
import com.estockMarket.companyService.command.api.commands.DeleteCompanyCommand;
import com.estockMarket.companyService.model.CompanyWriteModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;



@RestController
@RequestMapping("/api/v1.0/market/company/")
public class CompanyCommandController {

	 private static final Logger logger = LoggerFactory.getLogger(CompanyCommandController.class);

	private CommandGateway commandGateway;

	public CompanyCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
	@PostMapping("register")
	@Operation(summary = "Register a new company", security = @SecurityRequirement(name = "bearerAuth"))
	public String register(@RequestBody CompanyWriteModel request) {
		
		logger.info("Request to save:" + request.toString());
		CompanyCreateCommand cmd = CompanyCreateCommand.builder()
								.ceoName(request.getCeoName())
								.companyCode(request.getCompanyCode())
								.companyName(request.getCompanyName())
								.enlistedStockMarkets(request.getEnlistedStockMarkets())
								.turnOver(request.getTurnOver())
								.website(request.getWebsite())
								.uuid(UUID.randomUUID().toString())
								.build();
		String result = commandGateway.sendAndWait(cmd);
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

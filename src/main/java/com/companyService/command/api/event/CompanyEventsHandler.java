package com.companyService.command.api.event;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.companyService.data.Company;
import com.companyService.data.CompanyJpaRepo;
import com.companyService.model.CompanyWriteModel;



@Component
public class CompanyEventsHandler {
	private static final Logger logger = LoggerFactory.getLogger(CompanyEventsHandler.class);
	
	@Value("${stockServiceBaseUrl}")
	private String stockServiceBaseUrl;
	
	@Autowired
	private CompanyJpaRepo companyRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@EventHandler
	public void on(CompanyCreatedEvent companyCreatedEvent) {
		
		Company company = Company.builder()
													.companyCode(companyCreatedEvent.getCompanyCode())
													.companyName(companyCreatedEvent.getCompanyName())
													.ceoName(companyCreatedEvent.getCeoName())
													.website(companyCreatedEvent.getWebsite())
													.turnOver(companyCreatedEvent.getTurnOver())
													.enlistedStockMarkets(companyCreatedEvent.getEnlistedStockMarkets())
													.build();
		
		companyRepo.save(company);
		
		logger.info("Saved the comapny with company code:" + company.getCompanyCode());
	}

	@EventHandler
	public void on(CompanyDeletedEvent companyDeletedEvent) {
		companyRepo.deleteById(companyDeletedEvent.getCompanyCode());
		String url = UriComponentsBuilder.fromHttpUrl(stockServiceBaseUrl).path("/delete/" + companyDeletedEvent.getCompanyCode())
				.toUriString();

		HttpEntity<Void> entity = new HttpEntity<>(null);
	
		logger.info("DELETE request to stock service:" + url);

		try {
			restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		logger.info("Deleted company profile with code: " + companyDeletedEvent.getCompanyCode());
		
	}
}

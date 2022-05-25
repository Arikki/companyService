package com.estockMarket.companyService.events;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.estockMarket.companyService.data.Company;
import com.estockMarket.companyService.data.CompanyJpaRepo;

@Component
public class CompanyEventsHandler {
	private static final Logger logger = LoggerFactory.getLogger(CompanyEventsHandler.class);
	
	@Autowired
	private CompanyJpaRepo repo;
	
	@Value("${stockServiceBaseUrl}")
	private String stockServiceBaseUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@EventHandler
	public void register(CompanyCreatedEvent event) {
		
		Company company = Company.builder()
						.ceoName(event.getCeoName())
						.companyCode(event.getCompanyCode())
						.companyName(event.getCompanyName())
						.enlistedStockMarkets(event.getEnlistedStockMarkets())
						.turnOver(event.getTurnOver())
						.website(event.getWebsite())
						.build();
		
		repo.save(company);
		logger.info("Saved company with code: " + event.getCompanyCode());
	}
	
	@EventHandler
	public void delete(CompanyDeletedEvent event) {
		repo.deleteById(event.getCompanyCode());
		String url = UriComponentsBuilder.fromHttpUrl(stockServiceBaseUrl).path("/delete/" + event.getCompanyCode())
				.toUriString();

		HttpEntity<Void> entity = new HttpEntity<>(null);
	
		logger.info("DELETE request to stock service:" + url);

		try {
			restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		logger.info("Deleted company profile with code: " + event.getCompanyCode());
		
	}
		
	

}

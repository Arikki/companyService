package com.estockMarket.companyService.query.api.projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.axonframework.queryhandling.QueryHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.estockMarket.companyService.error.InvalidDataException;
import com.estockMarket.companyService.model.CompanyReadModel;
import com.estockMarket.companyService.model.Stock;
import com.estockMarket.companyService.query.api.queries.GetCompaniesQuery;
import com.estockMarket.companyService.query.api.queries.GetCompanyQuery;






@Component
public class CompanyProjection {

	private static final Logger logger = LoggerFactory.getLogger(CompanyProjection.class);
	
	@Value("${stockServiceBaseUrl}")
	private String stockServiceBaseUrl;
	
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@QueryHandler
	public CompanyReadModel getCompany(GetCompanyQuery getCompanyQuery) throws InvalidDataException {
//		Stock stock = new Stock();
		Query query = new Query();
		
		query.addCriteria(Criteria.where("_id").is(getCompanyQuery.getCompanyCode())
				.andOperator(Criteria.where("__deleted").is("false")));
	CompanyReadModel company =	 mongoTemplate.findOne(query, CompanyReadModel.class);
	
	if(Objects.isNull(company)) {
		throw new InvalidDataException("Company code not found");
	}

	String url = UriComponentsBuilder.fromHttpUrl(stockServiceBaseUrl).path("/getLatestStock/" + getCompanyQuery.getCompanyCode())
			.toUriString();

	HttpEntity<Void> entity = new HttpEntity<>(null);

	logger.info("GET request to stock service:" + url);

	try {
//		stock .add( restTemplate.exchange(url, HttpMethod.GET, entity, Stock.class).getBody());
		
		company.setStock(restTemplate.exchange(url, HttpMethod.GET, entity, Stock.class).getBody());
	} catch (Exception e) {
		logger.info(e.getMessage());
		e.printStackTrace();
	}
//	company.getStocks().add(stock);
		 return company;
		
	}
	
	
	@QueryHandler
	public List<CompanyReadModel> getCompanies(GetCompaniesQuery getCompaniesQuery){
		
		List<Stock> stockList = new ArrayList<Stock>();


		Query query = new Query();
		query.addCriteria(Criteria.where("__deleted").is("false"));
		List<CompanyReadModel> companyList = mongoTemplate.find(query,CompanyReadModel.class);
		
		logger.info("Number of companies fetched:" + companyList.size());
		
		String url = UriComponentsBuilder.fromHttpUrl(stockServiceBaseUrl).path("/getall")
				.toUriString();

		HttpEntity<Void> entity = new HttpEntity<>(null);

		logger.info("GET request to stock service:" + url);

		try {
			stockList = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Stock>>() {
			}).getBody();
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		
		Map<String,Stock> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getCompany_code, Function.identity()));
		logger.info(stockMap.toString());
		companyList.stream().forEach(company->{
			company.setStock(stockMap.get(company.get_id()));
		});
		
		logger.info("Fetch all comapnies end");
		return companyList;
	}
	
	
}

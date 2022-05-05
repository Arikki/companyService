package com.companyService.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "company")
public class CompanyReadModel {

	@Id
	private String _id;

	private String company_name;

	private String ceo_name;

	private BigDecimal turn_over;

	private String website;

	private String enlisted_stock_markets;
	
	private Stock stock;
	
}

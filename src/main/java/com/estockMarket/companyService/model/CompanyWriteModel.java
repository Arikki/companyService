package com.estockMarket.companyService.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyWriteModel {
	
	@NotEmpty(message = "cannot be empty or null")
	private String companyCode;

	@NotEmpty(message = "cannot be empty or null")
	private String companyName;

	@NotEmpty(message = "cannot be empty or null")
	private String ceoName;

	@NotNull(message = "cannot be empty or null")
	@DecimalMin("100000000.01")
	private BigDecimal turnOver;

	@NotEmpty(message = "cannot be empty or null")
	private String website;

	
	@NotEmpty(message = "cannot be empty or null")
	private String enlistedStockMarkets;

}

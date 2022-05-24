package com.estockMarket.companyService.data;

import java.math.BigDecimal;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "company")
public class Company {

	@Id
	@Column(name = "companyCode")
	private String companyCode;

	@Column(name =  "companyName")
	private String companyName;

	@Column(name =  "ceoName")
	private String ceoName;

	@Column(name =  "turnOver")
	private BigDecimal turnOver;

	@Column(name =  "website")
	private String website;

	
	@Column(name = "enlistedStockMarkets", columnDefinition = "text")
	private String enlistedStockMarkets;

}

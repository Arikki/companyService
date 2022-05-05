package com.companyService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Stock {

	private String company_code;
	private String date;
	private String time;
	private Double stock_price;
}

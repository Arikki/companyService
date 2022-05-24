package com.estockMarket.companyService.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info=@Info(title = "Company API", description = "Handles company details"))
//@SecurityScheme(
//	    name = "bearerAuth",
//	    type = SecuritySchemeType.HTTP,
//	    bearerFormat = "JWT",
//	    scheme = "bearer"
//	)
public class OpenApi30Config {

}

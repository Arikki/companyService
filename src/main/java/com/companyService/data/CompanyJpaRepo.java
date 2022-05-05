package com.companyService.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.companyService.model.CompanyWriteModel;

@Repository
public interface CompanyJpaRepo extends JpaRepository<Company, String>{

	
}

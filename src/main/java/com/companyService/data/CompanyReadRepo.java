package com.companyService.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.companyService.model.CompanyReadModel;

@Repository
@EnableMongoRepositories
public interface CompanyReadRepo extends MongoRepository<CompanyReadModel, String>{

}

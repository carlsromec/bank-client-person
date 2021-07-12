package com.everis.bankclientepersonal.model.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.bankclientepersonal.model.document.CustomerPersonDoc;

public interface CustomerPersonDao extends ReactiveMongoRepository<CustomerPersonDoc, String> {

}

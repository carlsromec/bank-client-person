package com.everis.bankclientepersonal.model.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.bankclientepersonal.model.document.CategoryDoc;

public interface CategoryDao extends ReactiveMongoRepository<CategoryDoc, String> {

}

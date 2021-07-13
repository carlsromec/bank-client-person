package com.everis.bankclientepersonal.model.service;

import com.everis.bankclientepersonal.model.document.CategoryDoc;
import com.everis.bankclientepersonal.model.document.CustomerPersonDoc;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerPersonService {
	
	public Flux<CustomerPersonDoc> findAll();
	public Mono<CustomerPersonDoc> findById(String id);
	public Mono<CustomerPersonDoc> save(CustomerPersonDoc customerPersonDoc);
	public Mono<Void> delete(CustomerPersonDoc customerPersonDoc);
	
	public Flux<CategoryDoc> findAllCategory();
	public Mono<CategoryDoc> findByIdCategory(String id);
	public Mono<CategoryDoc> saveCategory(CategoryDoc categoryDoc);

}




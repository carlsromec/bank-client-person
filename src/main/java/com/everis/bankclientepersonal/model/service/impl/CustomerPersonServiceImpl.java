package com.everis.bankclientepersonal.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.bankclientepersonal.model.dao.CategoryDao;
import com.everis.bankclientepersonal.model.dao.CustomerPersonDao;
import com.everis.bankclientepersonal.model.document.CategoryDoc;
import com.everis.bankclientepersonal.model.document.CustomerPersonDoc;
import com.everis.bankclientepersonal.model.service.CustomerPersonService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerPersonServiceImpl implements CustomerPersonService {
	
	@Autowired
	private CustomerPersonDao customerPersonDao;
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Flux<CustomerPersonDoc> findAll() {
		return customerPersonDao.findAll();
	}

	@Override
	public Mono<CustomerPersonDoc> findAllId(String id) {
		return customerPersonDao.findById(id);
	}

	@Override
	public Mono<CustomerPersonDoc> save(CustomerPersonDoc customerPersonDoc) {
		return customerPersonDao.save(customerPersonDoc);
	}

	@Override
	public Mono<Void> delete(CustomerPersonDoc customerPersonDoc) {
		return customerPersonDao.delete(customerPersonDoc);
	}

	@Override
	public Flux<CategoryDoc> findAllCategory() {
		return categoryDao.findAll();
	}

	@Override
	public Mono<CategoryDoc> findAllIdCategory(String id) {
		return categoryDao.findById(id);
	}

	@Override
	public Mono<CategoryDoc> saveCategory(CategoryDoc categoryDoc) {
		return categoryDao.save(categoryDoc);
	}

}

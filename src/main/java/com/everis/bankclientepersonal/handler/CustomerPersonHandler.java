package com.everis.bankclientepersonal.handler;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.everis.bankclientepersonal.model.document.CustomerPersonDoc;
import com.everis.bankclientepersonal.model.service.CustomerPersonService;

import reactor.core.publisher.Mono;

@Component
public class CustomerPersonHandler {
	
	@Autowired
	private CustomerPersonService customerPersonService;
	
	public Mono<ServerResponse> listCustomerPerson(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(customerPersonService.findAll(),CustomerPersonDoc.class);
	}
	
	public Mono<ServerResponse> listCustomerPersonId(ServerRequest request){
		String id = request.pathVariable("id");
		return customerPersonService.findById(id).flatMap(p -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(BodyInserters.fromObject(p)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> addCustomerPerson(ServerRequest request){
		Mono<CustomerPersonDoc> person = request.bodyToMono(CustomerPersonDoc.class);
		
		return person.flatMap(p ->{
			return customerPersonService.save(p);
		}).flatMap(p->ServerResponse
				.created(URI.create("/api/v2/customerperson/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(BodyInserters.fromObject(p)));
	}
	
	public Mono<ServerResponse> editCustomerPerson(ServerRequest request){
		Mono<CustomerPersonDoc> person = request.bodyToMono(CustomerPersonDoc.class);
		String id = request.pathVariable("id");
		
		Mono<CustomerPersonDoc> persondb = customerPersonService.findById(id);
		return persondb.zipWith(person, (db, req) -> {
			db.setDni(req.getDni());
			db.setLastname(req.getLastname());
			db.setFirstname(req.getFirstname());
			db.setTelephone(req.getTelephone());
			db.setDirection(req.getDirection());
			db.setCategoryDoc(req.getCategoryDoc());
			return db;
			}).flatMap(p -> ServerResponse.created(URI.create("/api/v2/customerperson/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(customerPersonService.save(p),CustomerPersonDoc.class))
				.switchIfEmpty(ServerResponse.notFound().build());	
	}
	
	
	public Mono<ServerResponse> deleteCustomerPerson(ServerRequest request){
		String id = request.pathVariable("id");
		Mono<CustomerPersonDoc> persondb = customerPersonService.findById(id);
		
		return persondb.flatMap(p -> customerPersonService.delete(p).then(ServerResponse.noContent().build()))
				.switchIfEmpty(ServerResponse.notFound().build());
		
	}
	
	
	

}

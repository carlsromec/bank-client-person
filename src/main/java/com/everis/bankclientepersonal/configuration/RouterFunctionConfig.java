package com.everis.bankclientepersonal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.everis.bankclientepersonal.model.document.CustomerPersonDoc;
import com.everis.bankclientepersonal.model.service.CustomerPersonService;

@Configuration
public class RouterFunctionConfig {
	
	@Autowired
	private CustomerPersonService customerPersonService;
	
	@Bean
	public RouterFunction<ServerResponse> routers(){
		return RouterFunctions.route(RequestPredicates.GET("/api/v2/customerperson"),request ->{
			return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(customerPersonService.findAll(),CustomerPersonDoc.class);
		});
	}

}
